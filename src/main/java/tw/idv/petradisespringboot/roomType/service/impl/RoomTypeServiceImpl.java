package tw.idv.petradisespringboot.roomType.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tw.idv.petradisespringboot.room.vo.Room;
import tw.idv.petradisespringboot.roomType.repo.RoomPicRepository;
import tw.idv.petradisespringboot.roomType.repo.RoomTypeRepository;
import tw.idv.petradisespringboot.roomType.service.RoomTypeService;
import tw.idv.petradisespringboot.roomType.vo.RoomPic;
import tw.idv.petradisespringboot.roomType.vo.RoomType;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {
    private final RoomTypeRepository typeRepository;
    private final RoomPicRepository picRepository;


    public RoomTypeServiceImpl(RoomTypeRepository roomTypeRepository, RoomPicRepository roomPicRepository) {
        this.typeRepository = roomTypeRepository;
        this.picRepository = roomPicRepository;
    }

    //取得該業主的所有房型
    @Override
    public List<RoomType> getByHotelId(Integer hotelId) {

        return typeRepository.findByHotelId(hotelId);

    }

    //新增房型
    @Override
    public RoomType addNewRoomType(RoomType newRoomType) {
        return typeRepository.save(newRoomType);
    }

    //取得特定房型
    @Override
    public RoomType getRoomType(Integer roomTypeId) {
        return typeRepository.findById(roomTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("RoomType not found with id " + roomTypeId));

    }

    //新增房間時更新房型數量
    @Override
    public void updateRoomType(Integer roomTypeId) {
        RoomType roomType = typeRepository.findById(roomTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("RoomType not found with id " + roomTypeId));

        roomType.setRoomTypeAmount(roomType.getRoomTypeAmount() + 1);  // 增加房間數量

        typeRepository.save(roomType);
    }


    //更新房型
    @Override
    public RoomType updateRoomType(Integer roomTypeId, RoomType roomType, MultipartFile file1, MultipartFile file2) {
        // 拿到原有的房型資訊
        RoomType existingRoomType = typeRepository.findById(roomTypeId)//找到指定的roomtypeid
                .orElseThrow(() -> new RoomTypeNotFoundException(roomTypeId));

        // 透過roomtypeid找到對應的圖片
        List<RoomPic> existingPics = picRepository.findByRoomType_RoomTypeId(roomTypeId);

        // 更新房型資訊
        // 把roomtype物件裡面的值設定給existingRoomType
        Character roomTypeSaleStatus = roomType.getRoomTypeSaleStatus();
        if (roomTypeSaleStatus != null) {
            existingRoomType.setRoomTypeSaleStatus(roomTypeSaleStatus);
            if (roomTypeSaleStatus == '0') {
                for (Room room : existingRoomType.getRooms()) {
                    room.setRoomSaleStatus('0');
                }
            } else if (roomTypeSaleStatus == '1') {
                for (Room room : existingRoomType.getRooms()) {
                    room.setRoomSaleStatus('1');
                }
            }
        }
        existingRoomType.setRoomTypeName(roomType.getRoomTypeName());
//        existingRoomType.setRoomTypeSaleStatus(roomType.getRoomTypeSaleStatus());
        existingRoomType.setRoomTypePrice(roomType.getRoomTypePrice());
        existingRoomType.setRoomPetType(roomType.getRoomPetType());
        existingRoomType.setRoomTypeSize(roomType.getRoomTypeSize());
        existingRoomType.setRoomTypeAbout(roomType.getRoomTypeAbout());

        MultipartFile[] files = {file1, file2}; //把圖片們裝進陣列 處理待處理的圖片
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            if (!file.isEmpty()) {
                RoomPic roomPic;
                if (existingPics.size() > i) {
                    // existingPics.size() 表格內已存在的圖片數量
//                  System.out.println("existingPics.size() = " + existingPics.size());
                    roomPic = existingPics.get(i); //把圖片表格裡的第i+1張照片拿出來
                } else {
                    roomPic = new RoomPic();// 新增圖片物件
                    roomPic.setRoomType(existingRoomType);//關聯roomtype屬性跟圖片
                    existingPics.add(roomPic);
                }
                try {
                    byte[] picData = file.getBytes(); //透過getBytes() 取得圖片陣列
                    roomPic.setRoomPic(picData);//更新圖片表格的圖片
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        existingRoomType.setRoomPics(existingPics);//更新房型vo裡的圖片
        RoomType updatedRoomType = typeRepository.save(existingRoomType);
        return updatedRoomType;
    }

    class RoomTypeNotFoundException extends RuntimeException {
        RoomTypeNotFoundException(Integer id) {
            super("找不到業主ID: " + id);
        }
    }

    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
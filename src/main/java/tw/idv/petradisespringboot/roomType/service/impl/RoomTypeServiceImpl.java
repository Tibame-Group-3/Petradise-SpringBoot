package tw.idv.petradisespringboot.roomType.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
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

    @Override
    public List<RoomType> getByHotelId(Integer hotelId) {

        return typeRepository.findByHotelId(hotelId);

    }


    @Override
    public RoomType addNewRoomType(RoomType newRoomType) {
        return typeRepository.save(newRoomType);
    }

    @Override
    public RoomType getRoomType(Integer roomTypeId) {
        return typeRepository.findById(roomTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("RoomType not found with id " + roomTypeId));

    }


    @Override
    public RoomType updateRoomType(Integer roomTypeId, RoomType roomType, MultipartFile file1, MultipartFile file2) {
        // 获取已存在的房型信息
        RoomType existingRoomType = typeRepository.findById(roomTypeId)
                .orElseThrow(() -> new RoomTypeNotFoundException(roomTypeId));

        // Check if RoomPic exists and handle nulls
        List<RoomPic> existingPics = picRepository.findByRoomType_RoomTypeId(roomTypeId);

        for (RoomPic roomPic : existingPics) {
            if (roomPic.getRoomPic() != null) {
                // Do something with roomPic.getRoomPic()
            } else {
                // Handle the case where roomPic is null
            }
        }

        // 更新房型基础信息
        existingRoomType.setRoomTypeName(roomType.getRoomTypeName());
        existingRoomType.setRoomTypeSaleStatus(roomType.getRoomTypeSaleStatus());
        existingRoomType.setRoomTypePrice(roomType.getRoomTypePrice());
        existingRoomType.setRoomPetType(roomType.getRoomPetType());
        existingRoomType.setRoomTypeSize(roomType.getRoomTypeSize());
        existingRoomType.setRoomTypeAbout(roomType.getRoomTypeAbout());

        MultipartFile[] files = {file1, file2};

        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            if (!file.isEmpty()) {
                RoomPic roomPic;
                if (existingPics.size() > i) {
                    // 如果数据库中已经有这张图片，则更新
                    roomPic = existingPics.get(i);
                } else {
                    // 否则新增一张图片
                    roomPic = new RoomPic();
                    roomPic.setRoomType(existingRoomType);
                    existingPics.add(roomPic);
                }

                try {
                    byte[] picData = file.getBytes();
                    roomPic.setRoomPic(picData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        existingRoomType.setRoomPics(existingPics);
        RoomType updatedRoomType = typeRepository.save(existingRoomType);
        return updatedRoomType;
    }




    @Override
    public RoomPic getRoomPic(Integer roomTypeId, Integer roomPicId) {
        List<RoomPic> all = picRepository.findAll();
        for (RoomPic roomPic:
             all) {
            if (roomPic.getRoomType().getRoomTypeId() == roomTypeId && roomPic.getRoomPicId() == roomPicId) {
                return roomPic;
            }
        }
        return null;
    }


    class RoomTypeNotFoundException extends RuntimeException {
    RoomTypeNotFoundException(Integer id) {
        super("找不到業主ID: " + id);

    }

}

class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}}
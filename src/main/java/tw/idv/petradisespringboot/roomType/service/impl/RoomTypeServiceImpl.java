package tw.idv.petradisespringboot.roomType.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import tw.idv.petradisespringboot.roomType.repo.RoomPicRepository;
import tw.idv.petradisespringboot.roomType.repo.RoomTypeRepository;
import tw.idv.petradisespringboot.roomType.service.RoomTypeService;
import tw.idv.petradisespringboot.roomType.vo.RoomPic;
import tw.idv.petradisespringboot.roomType.vo.RoomType;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {
	private final RoomTypeRepository typeRepository;
	private final RoomPicRepository picRepository;

//	public RoomTypeServiceImpl(RoomTypeRepository repository) {
//
//		this.repository = repository;
//	}

	public RoomTypeServiceImpl(RoomTypeRepository roomTypeRepository, RoomPicRepository roomPicRepository) {
		this.typeRepository = roomTypeRepository;
		this.picRepository = roomPicRepository;
	}

	@Override
	public List<RoomType> getByHotelId(Integer hotelId) {

		return typeRepository.findByHotelId(hotelId);

	}

	

//	 @Override
//	 public RoomType addNewRoomType(RoomType roomType, List<RoomPic> roomPics) {
//		    // 先保存RoomType
//		    RoomType savedRoomType = typeRepository.save(roomType);
//
//		    // 遍歷保存RoomType關聯的RoomPic
//		    for (RoomPic roomPic : roomPics) {
//		        roomPic.setRoomTypeId(savedRoomType.getRoomTypeId()); // 假设RoomPic有一个setRoomTypeId方法
//		        picRepository.save(roomPic);
//		    }
//
//		    // 返回新建的RoomType
//		    return savedRoomType;
//		}
	@Override
	public RoomType addNewRoomType(RoomType newRoomType) {
		return typeRepository.save(newRoomType);
	}

}

class RoomTypeNotFoundException extends RuntimeException {
	RoomTypeNotFoundException(Integer id) {
		super("找不到業主ID: " + id);

	}
}
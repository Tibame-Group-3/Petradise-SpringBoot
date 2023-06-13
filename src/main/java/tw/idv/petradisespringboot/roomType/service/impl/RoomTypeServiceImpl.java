package tw.idv.petradisespringboot.roomType.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import tw.idv.petradisespringboot.roomType.repo.RoomPicRepository;
import tw.idv.petradisespringboot.roomType.repo.RoomTypeRepository;
import tw.idv.petradisespringboot.roomType.service.RoomTypeService;
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
	@Override
	public RoomType addRoomTypeByHotelId(Integer hotelId) {
		
		return null;
	}

}

class RoomTypeNotFoundException extends RuntimeException {
	RoomTypeNotFoundException(Integer id) {
		super("找不到業主ID: " + id);

	}
}
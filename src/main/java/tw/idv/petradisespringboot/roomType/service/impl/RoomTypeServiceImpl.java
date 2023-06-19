package tw.idv.petradisespringboot.roomType.service.impl;

import java.util.List;
import java.util.Optional;

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
}
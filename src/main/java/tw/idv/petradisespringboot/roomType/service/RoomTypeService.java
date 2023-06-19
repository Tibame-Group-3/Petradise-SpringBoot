package tw.idv.petradisespringboot.roomType.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import tw.idv.petradisespringboot.roomType.vo.RoomPic;
import tw.idv.petradisespringboot.roomType.vo.RoomType;

public interface RoomTypeService {
	List<RoomType> getByHotelId(Integer hotelId);

	
	RoomType addNewRoomType(RoomType newRoomType);


	RoomType getRoomType(Integer roomTypeId);




	RoomType updateRoomType(Integer roomTypeId, RoomType roomType, MultipartFile file1, MultipartFile file2);

	RoomType getRoomTypeWithPics(Integer roomTypeId);
}

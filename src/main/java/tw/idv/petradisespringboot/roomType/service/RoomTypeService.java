package tw.idv.petradisespringboot.roomType.service;

import java.util.List;

import tw.idv.petradisespringboot.roomType.vo.RoomType;

public interface RoomTypeService {
	List<RoomType> getByHotelId(Integer hotelId);

	
	RoomType addNewRoomType(RoomType newRoomType);


	RoomType getRoomType(Integer roomTypeId);

}

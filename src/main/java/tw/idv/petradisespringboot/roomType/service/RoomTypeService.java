package tw.idv.petradisespringboot.roomType.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import tw.idv.petradisespringboot.roomType.dto.AllHotelDTO;
import tw.idv.petradisespringboot.roomType.dto.SingleHotelDTO;
import tw.idv.petradisespringboot.roomType.dto.searchHotelDTO;
import tw.idv.petradisespringboot.roomType.vo.RoomType;

import java.util.List;

public interface RoomTypeService {
    List<RoomType> getByHotelId(Integer hotelId);


    RoomType addNewRoomType(RoomType newRoomType);


    RoomType getRoomType(Integer roomTypeId);


    void updateRoomType(Integer roomTypeId);


    RoomType updateRoomType(Integer roomTypeId, RoomType roomType, MultipartFile file1, MultipartFile file2);

    SingleHotelDTO getSingleHotel(Integer hotelId, String petType, Character roomTypeSize);

	public List<AllHotelDTO> searchHotels(searchHotelDTO searchDto) ;

}

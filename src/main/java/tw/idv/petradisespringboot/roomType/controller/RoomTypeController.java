package tw.idv.petradisespringboot.roomType.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.petradisespringboot.roomType.service.RoomTypeService;
import tw.idv.petradisespringboot.roomType.vo.RoomType;
@RestController
public class RoomTypeController {
	private final RoomTypeService service;
	
	public RoomTypeController(RoomTypeService service) {
		super();
		this.service = service;
	}


	@GetMapping("/roomTypes/hotelId/{hotelId}")
	List<RoomType> getByHotelId(@PathVariable Integer hotelId) {
		return service.getByHotelId(hotelId);
	}
	@PostMapping("/addRoomTypes/hotelId/{hotelId}")
	RoomType addRoomTypeByHotelId(@PathVariable Integer hotelId) {
		return service.addRoomTypeByHotelId(hotelId);
	}
}

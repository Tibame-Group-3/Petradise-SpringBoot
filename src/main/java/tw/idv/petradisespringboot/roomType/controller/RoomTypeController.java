package tw.idv.petradisespringboot.roomType.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.idv.petradisespringboot.roomType.service.RoomTypeService;
import tw.idv.petradisespringboot.roomType.vo.RoomType;

@Controller
public class RoomTypeController {
	private final RoomTypeService service;

	public RoomTypeController(RoomTypeService service) {
		super();
		this.service = service;
	}

	@GetMapping("/roomTypes/hotelId/{hotelId}")
	@ResponseBody
	List<RoomType> getByHotelId(@PathVariable Integer hotelId) {
		return service.getByHotelId(hotelId);
	}

	@PostMapping("/addRoomType")
	@ResponseBody
	public void addRoomType(@RequestBody RoomType newRoomType) {
		service.addNewRoomType(newRoomType);
	}

	@GetMapping("/roomTypes/{roomTypeId}")
	@ResponseBody
	public RoomType getRoomType(@PathVariable Integer roomTypeId) {
		return service.getRoomType(roomTypeId);
	}
}

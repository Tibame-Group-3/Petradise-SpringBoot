package tw.idv.petradisespringboot.room.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.room.service.RoomService;
import tw.idv.petradisespringboot.room.vo.Room;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/hotelId/{hotelId}")
    @ResponseBody
    public List<Map<String, Object>> getRoomsByHotelId(@PathVariable Integer hotelId) {
        return roomService.getRoomsByHotelId(hotelId);
    }

    @PostMapping("/addRoom")
    public ResponseEntity<Room> addRoom(@RequestBody Room newRoom, @RequestParam("roomTypeId") Integer roomTypeId) {
        Room room = roomService.addNewRoom(newRoom, roomTypeId);
        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }

    @PostMapping("/updateRoom/{roomId}")
    @ResponseBody
    public String updateRoom(@PathVariable Integer roomId, @RequestBody Room updatedRoom) {
        Room room = roomService.getRoomById(roomId);
        if (room == null) {
            return "Room not found";
        }
        room.setRoomName(updatedRoom.getRoomName());
        room.setRoomType(updatedRoom.getRoomType());  // 使用 updatedRoom.getRoomType() 來設定房型對象
        room.setPetName(updatedRoom.getPetName());
        room.setRoomSaleStatus(updatedRoom.getRoomSaleStatus());
        room.setRoomStatus(updatedRoom.getRoomStatus());

        roomService.saveRoom(room);
        return "Room updated successfully";
    }
}


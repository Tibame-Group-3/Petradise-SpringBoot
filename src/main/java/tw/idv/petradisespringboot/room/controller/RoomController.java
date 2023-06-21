package tw.idv.petradisespringboot.room.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

}


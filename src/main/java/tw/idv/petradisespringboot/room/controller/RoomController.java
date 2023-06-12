package tw.idv.petradisespringboot.room.controller;


import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.room.service.RoomService;
import tw.idv.petradisespringboot.room.vo.Room;

import java.util.List;

@RestController
public class RoomController {

    RoomService service;

    RoomController(RoomService service) {
        this.service = service;
    }

    @GetMapping("/rooms/id/{id}")
    Room one(@PathVariable Integer id) {
        return service.findByID(id);
    }

    @PostMapping("/rooms/add")
    Room addRoom(@RequestBody Room room) {
        return service.add(room);
    }

    @GetMapping("/rooms/all")
    List<Room> all() {
        return service.findAll();
    }
}

package tw.idv.petradisespringboot.roomorder.controller;

import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.roomorder.service.RoomOrderService;
import tw.idv.petradisespringboot.roomorder.vo.RoomOrder;

import java.util.List;

@RestController
@RequestMapping("/room-order")
public class RoomOrderController {

    private final RoomOrderService service;
    public RoomOrderController(RoomOrderService service) {
        this.service = service;
    }

    @PostMapping("/customer/add")
    public RoomOrder add(@RequestBody RoomOrder order){
        return service.add(order);
    }
    @GetMapping("/customer/mem-id/{memId}")
    public List<RoomOrder> getRoomOrdersByMemId(@PathVariable Integer memId) {
        return service.getRoomOrdersByMemId(memId);
    }
    @GetMapping("/customer/pet-id/{petId}")
    public List<RoomOrder> getRoomOrdersByPetId(@PathVariable Integer petId) {
        return service.getRoomOrdersByPetId(petId);
    }
    @GetMapping("/customer/status/{status}")
    public List<RoomOrder> getRoomOrdersByStatus(@PathVariable Character status) {
        return service.getRoomOrdersByStatus(status);
    }

    @GetMapping("/all")
    public List<RoomOrder> getAllOrder(){
        return service.getAll();
    }

    @GetMapping("/id/{id}")
    public RoomOrder getRoomOrderById(@PathVariable Integer id){
        return service.getRoomOrderById(id);
    }

    @PutMapping("id/{id}/modify")
    public RoomOrder modify(@PathVariable Integer id, @RequestBody RoomOrder modifiedRoomOrder){
        return service.modify(id, modifiedRoomOrder);
    }
}

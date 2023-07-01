package tw.idv.petradisespringboot.roomorder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.roomorder.service.RoomOrderService;
import tw.idv.petradisespringboot.roomorder.vo.RoomOrder;

import java.util.List;
import java.util.Map;

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

    @PutMapping("id/{id}/change-status")
    public RoomOrder changeRoomOrderStatus(@PathVariable Integer id, @RequestBody Map<String, String> requestBody){
        String newStatus = requestBody.get("status");
        char status = newStatus.charAt(0);
        return service.changeRoomOrderStatus(id, status);
    }

    @GetMapping("/hotel-id/{hotelId}")
    public ResponseEntity<List<RoomOrder>> getRoomOrdersByHotelId(@PathVariable Integer hotelId) {
        List<RoomOrder> roomOrders = service.getRoomOrdersByHotelId(hotelId);
        return ResponseEntity.ok(roomOrders);
    }

}

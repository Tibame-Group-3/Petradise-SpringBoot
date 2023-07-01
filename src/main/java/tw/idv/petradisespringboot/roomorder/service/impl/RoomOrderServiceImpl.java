package tw.idv.petradisespringboot.roomorder.service.impl;

import org.springframework.stereotype.Service;
import tw.idv.petradisespringboot.roomorder.repo.RoomOrderRepository;
import tw.idv.petradisespringboot.roomorder.service.RoomOrderService;
import tw.idv.petradisespringboot.roomorder.vo.RoomOrder;

import java.util.List;
@Service
public class RoomOrderServiceImpl implements RoomOrderService {

    private final RoomOrderRepository repository;

    public RoomOrderServiceImpl(RoomOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public RoomOrder add(RoomOrder order) {
        return repository.save(order);
    }

    @Override
    public List<RoomOrder> getRoomOrdersByMemId(Integer memId) {
        return repository.findByMemId(memId);
    }

    @Override
    public List<RoomOrder> getRoomOrdersByStatus(Character status) {
        return repository.findByStatus(status);
    }

    @Override
    public List<RoomOrder> getAll() {
        return repository.findAll();
    }

    @Override
    public RoomOrder getRoomOrderById(Integer id) {
        return repository.findById(id).orElseThrow(null);
    }

    @Override
    public RoomOrder modify(Integer id, RoomOrder modifiedRoomOrder) {
        // Update the fields of the existing room order with the new values
        return repository.findById(id).map(existingRoomOrder ->{
//            existingRoomOrder.setRoomTypeId(modifiedRoomOrder.getRoomTypeId());
            existingRoomOrder.setRoomId(modifiedRoomOrder.getRoomId());
            existingRoomOrder.setPetName(modifiedRoomOrder.getPetName());
            existingRoomOrder.setOrderDate(modifiedRoomOrder.getOrderDate());
            existingRoomOrder.setCheckInDate(modifiedRoomOrder.getCheckInDate());
            existingRoomOrder.setCheckOutDate(modifiedRoomOrder.getCheckOutDate());
            existingRoomOrder.setStatus(modifiedRoomOrder.getStatus());
            existingRoomOrder.setOrigPrice(modifiedRoomOrder.getOrigPrice());
            existingRoomOrder.setFinalPrice(modifiedRoomOrder.getFinalPrice());
            existingRoomOrder.setSpecialReq(modifiedRoomOrder.getSpecialReq());
            return repository.save(existingRoomOrder);
        }).orElse(null);
    }

    @Override
    public RoomOrder changeRoomOrderStatus(Integer id, char newStatus) {
        return repository.findById(id).map(roomOrder -> {
            roomOrder.setStatus(newStatus);
            return repository.save(roomOrder);
        }).orElse(null);
    }

    @Override
    public List<RoomOrder> getRoomOrdersByHotelId(Integer hotelId) {
        return repository.findByHotelId(hotelId);
    }

}

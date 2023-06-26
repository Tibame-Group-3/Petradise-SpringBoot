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
    public List<RoomOrder> getRoomOrdersByPetId(Integer petId) {
        return repository.findByPetId(petId);
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
            existingRoomOrder.setRoomTypeId(modifiedRoomOrder.getRoomTypeId());
            existingRoomOrder.setRoomId(modifiedRoomOrder.getRoomId());
            existingRoomOrder.setPetId(modifiedRoomOrder.getPetId());
            existingRoomOrder.setOrderDate(modifiedRoomOrder.getOrderDate());
            existingRoomOrder.setCheckInDate(modifiedRoomOrder.getCheckInDate());
            existingRoomOrder.setCheckOutDate(modifiedRoomOrder.getCheckOutDate());
            existingRoomOrder.setStatus(modifiedRoomOrder.getStatus());
            existingRoomOrder.setOrigPrice(modifiedRoomOrder.getOrigPrice());
            existingRoomOrder.setFinalPrice(modifiedRoomOrder.getFinalPrice());
            existingRoomOrder.setBonus(modifiedRoomOrder.getBonus());
            existingRoomOrder.setSpecialReq(modifiedRoomOrder.getSpecialReq());
            return repository.save(existingRoomOrder);
        }).orElse(null);
    }
}

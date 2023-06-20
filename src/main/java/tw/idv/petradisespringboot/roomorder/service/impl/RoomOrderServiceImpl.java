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
}

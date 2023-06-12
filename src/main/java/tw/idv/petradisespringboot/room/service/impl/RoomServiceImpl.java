package tw.idv.petradisespringboot.room.service.impl;

import org.springframework.stereotype.Service;
import tw.idv.petradisespringboot.room.repo.RoomRepository;
import tw.idv.petradisespringboot.room.service.RoomService;
import tw.idv.petradisespringboot.room.vo.Room;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository repositoy;

    public RoomServiceImpl(RoomRepository repository) {
        this.repositoy = repository;
    }

    @Override
    public Room add(Room room) {
        return repositoy.save(room);
    }

    @Override
    public Room update(Room room) {
        return repositoy.save(room);
    }

    @Override
    public Room findByID(Integer id) {
        return repositoy
                .findById(id)
                .orElseThrow(() -> new RoomNotFoundException(id));
    }

    @Override
    public List<Room> findAll() {
        return repositoy.findAll();
    }
}

class RoomNotFoundException extends RuntimeException {
    RoomNotFoundException(Integer id) {
        super("Room not found, id: " + id);
    }
}
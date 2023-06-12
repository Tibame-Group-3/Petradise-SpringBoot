package tw.idv.petradisespringboot.room.service;

import tw.idv.petradisespringboot.room.vo.Room;

import java.util.List;

public interface RoomService {

    Room add(Room room);

    Room update(Room room);

    Room findByID(Integer id);

    List<Room> findAll();

}
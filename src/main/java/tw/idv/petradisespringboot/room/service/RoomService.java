package tw.idv.petradisespringboot.room.service;

import org.springframework.stereotype.Service;
import tw.idv.petradisespringboot.room.vo.Room;
import java.util.List;

@Service
public interface RoomService {
    List<Room> getRoomsByHotelId(Integer hotelId);
}

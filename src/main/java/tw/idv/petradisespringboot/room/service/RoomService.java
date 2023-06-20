package tw.idv.petradisespringboot.room.service;

import tw.idv.petradisespringboot.room.vo.Room;
import java.util.List;
import java.util.Map;


public interface RoomService {
    List<Map<String, Object>> getRoomsByHotelId(Integer hotelId);
}

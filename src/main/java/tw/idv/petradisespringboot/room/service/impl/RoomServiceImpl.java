package tw.idv.petradisespringboot.room.service.impl;

import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tw.idv.petradisespringboot.room.repo.RoomRepository;
import tw.idv.petradisespringboot.room.service.RoomService;
import tw.idv.petradisespringboot.room.vo.Room;
import tw.idv.petradisespringboot.roomType.vo.RoomType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Map<String, Object>> getRoomsByHotelId(Integer hotelId) {
        List<Room> rooms = roomRepository.findRoomsByHotelId(hotelId);
        List<Map<String, Object>> roomMaps = new ArrayList<>();
        for (Room room : rooms) {
            Map<String, Object> roomMap = new HashMap<>();
            roomMap.put("roomId", room.getRoomId());
            roomMap.put("roomTypeName", room.getRoomType().getRoomTypeName());
            roomMap.put("petName", room.getPetName());
            roomMap.put("roomName", room.getRoomName());
            roomMap.put("roomSaleStatus", room.getRoomSaleStatus());
            roomMap.put("roomStatus", room.getRoomStatus());
            roomMaps.add(roomMap);
        }
        return roomMaps;
    }

}

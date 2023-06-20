package tw.idv.petradisespringboot.roomorder.service;

import tw.idv.petradisespringboot.roomorder.vo.RoomOrder;

import java.util.List;

public interface RoomOrderService {

    RoomOrder add(RoomOrder order);
    List<RoomOrder> getRoomOrdersByMemId(Integer memId);
    List<RoomOrder> getRoomOrdersByPetId(Integer petId);
    List<RoomOrder> getRoomOrdersByStatus(Character status);
}

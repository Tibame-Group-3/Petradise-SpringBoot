package tw.idv.petradisespringboot.roomorder.service;

import tw.idv.petradisespringboot.roomorder.vo.RoomOrder;

import java.util.List;

public interface RoomOrderService {

    RoomOrder add(RoomOrder order);
    List<RoomOrder> getRoomOrdersByMemId(Integer memId);
    List<RoomOrder> getRoomOrdersByStatus(Character status);

    List<RoomOrder> getAll();
    RoomOrder getRoomOrderById(Integer id);
    RoomOrder modify(Integer id, RoomOrder modifiedRoomOrder);
    RoomOrder changeRoomOrderStatus(Integer id, char newStatus);


    List<RoomOrder> getRoomOrdersByHotelId(Integer hotelId);


}

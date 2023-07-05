package tw.idv.petradisespringboot.mall.service;

import java.util.List;

import tw.idv.petradisespringboot.mall.model.dto.AllOrderMasterDTO;
import tw.idv.petradisespringboot.mall.model.dto.CreateOrderDTO;
import tw.idv.petradisespringboot.mall.model.dto.OrderDetailDTO;
import tw.idv.petradisespringboot.mall.model.vo.OrderMaster;

public interface OrderService {

    OrderMaster createOrder(CreateOrderDTO dto);
    
    List<AllOrderMasterDTO> findAllOrderMaster();
    
//    List<OrderMaster> findOrderByMemberId(Integer memId);
    
    List<OrderMaster> getByMemIdAndOdStatusNot(Integer memId, Character odStatus);

    List<OrderDetailDTO> findOrderDetailById(Integer id);
    
    void updateOrderStatus(Integer odId, Character odStatus);
    
}

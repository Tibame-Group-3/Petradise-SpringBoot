package tw.idv.petradisespringboot.mall.service;

import java.util.List;

import tw.idv.petradisespringboot.mall.model.dto.AllOrderMasterDTO;
import tw.idv.petradisespringboot.mall.model.dto.CreateOrderDTO;
import tw.idv.petradisespringboot.mall.model.dto.OrderDetailDTO;
import tw.idv.petradisespringboot.mall.model.vo.OrderMaster;

public interface OrderService {

    OrderMaster createOrder(CreateOrderDTO dto);
    
    List<AllOrderMasterDTO> findAllOrderMaster();
    
    List<OrderDetailDTO> findOrderDetailById(Integer id);
    
    OrderMaster updateOrderStatus();
   
}

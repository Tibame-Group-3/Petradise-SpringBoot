package tw.idv.petradisespringboot.mall.service;

import java.util.List;

import tw.idv.petradisespringboot.mall.model.dto.CreateOrderDTO;
import tw.idv.petradisespringboot.mall.model.vo.OrderMaster;

public interface OrderService {

    OrderMaster createOrder(CreateOrderDTO dto);
    
    List<OrderMaster> getAll();
    
    OrderMaster updateOrder();
    
}

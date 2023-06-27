package tw.idv.petradisespringboot.mall.service;

import tw.idv.petradisespringboot.mall.model.vo.CreateOrderDTO;
import tw.idv.petradisespringboot.mall.model.vo.OrderMaster;

public interface OrderService {

    OrderMaster createOrder(CreateOrderDTO dto);
}

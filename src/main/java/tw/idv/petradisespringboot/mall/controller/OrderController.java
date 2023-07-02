package tw.idv.petradisespringboot.mall.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.petradisespringboot.mall.model.dto.AllOrderMasterDTO;
import tw.idv.petradisespringboot.mall.model.dto.CreateOrderDTO;
import tw.idv.petradisespringboot.mall.model.dto.OrderDetailDTO;
import tw.idv.petradisespringboot.mall.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    
    @GetMapping("/allOrder")
    public List<AllOrderMasterDTO> findAllOrderMaster() {
    	return orderService.findAllOrderMaster();
    }
    
    @GetMapping("/showOrderDetail/id={id}")
	public List<OrderDetailDTO> findOrderDetailById(@PathVariable Integer id) {
		return orderService.findOrderDetailById(id);
	}
    
    @PostMapping(value = "/add", consumes = {"application/json"})
    public ResponseEntity<?> addOrder(@RequestBody CreateOrderDTO dto) {
        return ResponseEntity.ok(orderService.createOrder(dto));
    }
    
}

package tw.idv.petradisespringboot.mall.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.petradisespringboot.mall.NotFoundException4OrderMaster;
import tw.idv.petradisespringboot.mall.model.dto.AllOrderMasterDTO;
import tw.idv.petradisespringboot.mall.model.dto.CreateOrderDTO;
import tw.idv.petradisespringboot.mall.model.dto.OrderDetailDTO;
import tw.idv.petradisespringboot.mall.model.vo.OrderMaster;
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

//    @GetMapping("/memberId={memberId}")
//    public List<OrderMaster> findOrdersByMemberId(@PathVariable Integer memberId) {
//        return orderService.findOrderByMemberId(memberId);
//    }

	@GetMapping("/memberIdAndOrderdStatusNot={memberId}")
	public List<OrderMaster> getByMemIdAndOdStatusNot(@PathVariable Integer memberId) {
		Character orderStatus = '1';
		return orderService.getByMemIdAndOdStatusNot(memberId, orderStatus);
	}

	@GetMapping("/showOrderDetail/id={id}")
	public List<OrderDetailDTO> findOrderDetailById(@PathVariable Integer id) {
		return orderService.findOrderDetailById(id);
	}

	@PostMapping(value = "/add", consumes = { "application/json" })
	public ResponseEntity<?> addOrder(@RequestBody CreateOrderDTO dto) {
		return ResponseEntity.ok(orderService.createOrder(dto));
	}

	@PostMapping(value="/updateOrderStatus", consumes = { "multipart/form-data" })
	public ResponseEntity<?> updateOrderStatus(@RequestParam("odId") Integer odId,
											   @RequestParam("odStatus") Character odStatus) {
		try {
			orderService.updateOrderStatus(odId, odStatus);
			return ResponseEntity.ok("Order status updated successfully!");
		} catch (NotFoundException4OrderMaster exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		}
	}

}

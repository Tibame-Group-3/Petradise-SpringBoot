package tw.idv.petradisespringboot.mall.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.idv.petradisespringboot.mall.model.vo.CreateOrderDTO;
import tw.idv.petradisespringboot.mall.service.OrderService;

@RestController
@RequestMapping("/order_master")
public class OrderController {

    private final OrderService orderService;

    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

//    前端會傳來的JSON格式
//    {
//        "orderMaster": {
//        "memId": 1,
//                "priceOri": 1800,
//                "priceShip": 100,
//                "priceOd": 1900,
//                "reciName": "王小明",
//                "reciPhone": "0912312312"
//    },
//        "products": [
//        {
//            "productId": 2,
//                "productAmount": 3
//        },
//        {
//            "productId": 3,
//                "productAmount": 2
//        }
//  ]
//    }
    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody CreateOrderDTO dto) {
        return ResponseEntity.ok(orderService.createOrder(dto));
    }
}

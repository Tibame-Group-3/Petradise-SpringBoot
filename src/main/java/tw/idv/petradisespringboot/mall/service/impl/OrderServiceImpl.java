package tw.idv.petradisespringboot.mall.service.impl;

import org.springframework.stereotype.Service;
import tw.idv.petradisespringboot.mall.model.repo.OrderDetailRepository;
import tw.idv.petradisespringboot.mall.model.repo.OrderMasterRepository;
import tw.idv.petradisespringboot.mall.model.vo.CreateOrderDTO;
import tw.idv.petradisespringboot.mall.model.vo.OrderDetail;
import tw.idv.petradisespringboot.mall.model.vo.OrderDetailCompositePK;
import tw.idv.petradisespringboot.mall.model.vo.OrderMaster;
import tw.idv.petradisespringboot.mall.service.OrderService;

import javax.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMasterRepository orderMasterRepository;
    private final OrderDetailRepository orderDetailRepository;

    OrderServiceImpl(OrderMasterRepository orderMasterRepository, OrderDetailRepository orderDetailRepository) {
        this.orderMasterRepository = orderMasterRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Transactional
    @Override
    public OrderMaster createOrder(CreateOrderDTO dto) {
        final var orderMaster = dto.getOrderMaster();
        final var savedOrderMaster = orderMasterRepository.save(orderMaster);
        final var products = dto.getProducts();
        final var orderMasterId = savedOrderMaster.getOdId();
        if (products != null) {
            products.forEach(product -> {

                final var productId = product.getProductId();
                final var productAmount = product.getProductAmount();

                // OrderDetail PK = OrderMaster ID + Product ID
                final var orderDetailPK = new OrderDetailCompositePK();
                orderDetailPK.setOdId(orderMasterId);
                orderDetailPK.setPdId(productId);

                var orderDetail = new OrderDetail();
                orderDetail.setId(orderDetailPK);
                orderDetail.setPdAmount(productAmount);
                orderDetailRepository.save(orderDetail);
            });
        }
        return savedOrderMaster;
    }
}

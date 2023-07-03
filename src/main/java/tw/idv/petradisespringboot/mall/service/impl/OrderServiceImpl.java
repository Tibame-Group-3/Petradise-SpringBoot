package tw.idv.petradisespringboot.mall.service.impl;

import org.springframework.stereotype.Service;

import tw.idv.petradisespringboot.mall.model.dto.CreateOrderDTO;
import tw.idv.petradisespringboot.mall.model.dto.OrderDetailDTO;
import tw.idv.petradisespringboot.mall.model.dto.AllOrderMasterDTO;
import tw.idv.petradisespringboot.mall.model.repo.OrderDetailRepository;
import tw.idv.petradisespringboot.mall.model.repo.OrderMasterRepository;
import tw.idv.petradisespringboot.mall.model.repo.ProductDAO;
import tw.idv.petradisespringboot.mall.model.vo.OrderDetail;
import tw.idv.petradisespringboot.mall.model.vo.OrderDetailCompositePK;
import tw.idv.petradisespringboot.mall.model.vo.OrderMaster;
import tw.idv.petradisespringboot.mall.model.vo.Product;
import tw.idv.petradisespringboot.mall.service.OrderService;
import tw.idv.petradisespringboot.member.repo.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

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

	@Override
	public List<AllOrderMasterDTO> findAllOrderMaster() {
		return orderMasterRepository.findAllOrderMaster();
	}

	@Override
	public List<OrderMaster> findOrderByMemberId(Integer memId) {
		return orderMasterRepository.findByMemId(memId);
	}

//	@Override
//	public List<AllOrderMasterDTO> findOrderByMemberId(Integer memId) {
//		List<OrderMaster> orderMasters = orderMasterRepository.findAllByMemId(memId);
//		
	// convert OrderMaster to AllOrderMasterDTO and return!
//		return orderMasters.stream().map(this::convertToAllOrderMasterDTO).collect(Collectors.toList());
//	}
//	
//    private AllOrderMasterDTO convertToAllOrderMasterDTO(OrderMaster orderMaster) {
//        
//    }

	@Override
	public List<OrderDetailDTO> findOrderDetailById(Integer id) {
		return orderDetailRepository.findOrderDetailById(id);
	}

	@Override
	public OrderMaster updateOrderStatus() {
		return null;
	}
}

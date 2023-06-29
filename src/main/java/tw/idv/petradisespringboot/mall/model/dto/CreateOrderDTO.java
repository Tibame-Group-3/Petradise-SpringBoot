package tw.idv.petradisespringboot.mall.model.dto;

import lombok.Data;
import tw.idv.petradisespringboot.mall.model.vo.OrderMaster;
import tw.idv.petradisespringboot.member.vo.Member;

import java.util.List;

@Data
public class CreateOrderDTO {

	private Member member;
    private OrderMaster orderMaster;
    private List<ProductDTO> products;

}

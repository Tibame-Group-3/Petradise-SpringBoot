package tw.idv.petradisespringboot.mall.model.dto;

import lombok.Data;
import tw.idv.petradisespringboot.mall.model.vo.OrderMaster;

import java.util.List;

@Data
public class CreateOrderDTO {

    private OrderMaster orderMaster;
    private List<ProductDTO> products;

}

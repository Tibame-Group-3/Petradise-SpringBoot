package tw.idv.petradisespringboot.mall.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderDTO {

    private OrderMaster orderMaster;
    private List<ProductDTO> products;

}

package tw.idv.petradisespringboot.mall.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDetailDTO {

	private Integer odId;
	private String name;
	private String pdName;
	private String pdType;
	private Date odDate;
	private Character odStatus;
	private Integer priceShip;
	private Integer priceOd;
	private String reciName;
	private String reciPhone;
	private String reciAdd;
	
}

package tw.idv.petradisespringboot.mall.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AllOrderMasterDTO {

	private Integer odId;
	private String name;
	private Date odDate;
	private Integer priceOd;
	private String reciName;
	private String reciPhone;
	private Character odStatus;
	
}

package tw.idv.petradisespringboot.mall.model.vo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "order_master")
public class OrderMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "od_id")
	private Integer odId;
	
	@Column(name = "mem_id")
	private Integer memId;
	
	@Column(name = "price_ori")
	private Integer priceOri;
	
	@Column(name = "price_dis")
	private Integer priceDis;
	
	@Column(name = "price_bonus")
	private Integer priceBonus;
	
	@Column(name = "price_ship")
	private Integer priceShip;
	
	@Column(name = "price_od")
	private Integer priceOd;
	
	@Column(name = "od_status")
	private Character odStatus;
	
	@Column(name = "od_pay")
	private Character odPay;
	
	@Column(name = "od_ship")
	private Character odShip;
	
	@Column(name = "od_note")
	private String odNote;
	
	@Column(name = "reci_name")
	private String reciName;
	
	@Column(name = "reci_phone")
	private String reciPhone;
	
	@Column(name = "reci_add")
	private String reciAdd;
	
	@Column(name = "od_date")
	private Date odDate;

}

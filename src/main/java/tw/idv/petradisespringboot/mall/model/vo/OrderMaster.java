package tw.idv.petradisespringboot.mall.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Date;
import java.util.List;

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

	@Column(name = "price_ship")
	private Integer priceShip;

	@Column(name = "price_od")
	private Integer priceOd;

	@Column(name = "od_status", insertable = false)
	private Character odStatus = '0';

	@Column(name = "od_pay")
	private Character odPay;

	@Column(name = "od_ship")
	private Character odShip;

	@Column(name = "reci_name")
	private String reciName;

	@Column(name = "reci_phone")
	private String reciPhone;

	@Column(name = "reci_add")
	private String reciAdd;

	@Column(name = "reci_store")
	private String reciStore;

	@Column(name = "od_date", insertable = false)
	private Date odDate;

//	@JsonManagedReference
//	@OneToMany(mappedBy = "orderMaster", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private List<OrderDetail> orderDetails;

}

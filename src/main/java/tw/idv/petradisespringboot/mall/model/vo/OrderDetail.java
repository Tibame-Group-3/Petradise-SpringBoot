package tw.idv.petradisespringboot.mall.model.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "order_detail")
@IdClass(OrderDetailCompositePK.class)
public class OrderDetail {

	@Id
	@Column(name = "od_id")
	private Integer odId;

	@Id
	@Column(name = "pd_id")
	private Integer pdId;

	@Column(name = "sale_pro_id")
	private Integer saleProId;

	@Column(name = "pd_amount")
	private Integer pdAmount;

	@Column(name = "rank_status")
	private Character rankStatus;

	@Override
	public String toString() {
		return "OrderDetail {" +
					"odId=" + odId +
					"pdId=" + pdId +
					"saleProId=" + saleProId +
					"pdAmount=" + pdAmount +
					"rankStatus=" + rankStatus +
					'}';
	}

}

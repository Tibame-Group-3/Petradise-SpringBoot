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

@Table(name = "sale")
@IdClass(SaleCompositePK.class)
public class Sale {

	@Id
	@Column(name = "pd_id")
	private Integer pdId;

	@Id
	@Column(name = "sale_pro_id")
	private Integer saleProId;
	
	@Column(name = "sale_discount")
	private Double saleDiscount;
	
	@Override
	public String toString() {
		return "Sale {" +
				"pdId=" + pdId +
				"saleProId=" + saleProId +
				"saleDiscount=" + saleDiscount +
				'}';
	}
	
}

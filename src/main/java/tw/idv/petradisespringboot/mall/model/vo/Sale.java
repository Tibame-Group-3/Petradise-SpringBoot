package tw.idv.petradisespringboot.mall.model.vo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sale")
public class Sale {
	
	@Id
	@Column(name = "sale_pro_id")
	private Integer saleProId;

	@Column(name = "pd_type")
	private Integer pdType;
	
	@Column(name = "sale_discount")
	private Double saleDiscount;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="sale_pro_id", referencedColumnName = "sale_pro_id",insertable = false, updatable=false)
	private SaleProject saleProject;
	
	@Override
	public String toString() {
		return "Sale {" +
				"saleProId=" + saleProId +
				"pdType=" + pdType +
				"saleDiscount=" + saleDiscount +
				'}';
	}
	
}

package tw.idv.petradisespringboot.mall.model.vo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
	
	@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "pd_id", referencedColumnName = "pd_id",insertable = false, updatable=false)
    private Product product;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="sale_pro_id", referencedColumnName = "sale_pro_id",insertable = false, updatable=false)
	private SaleProject saleProject;
	
	@Override
	public String toString() {
		return "Sale {" +
				"pdId=" + pdId +
				"saleProId=" + saleProId +
				"saleDiscount=" + saleDiscount +
				'}';
	}
	
}

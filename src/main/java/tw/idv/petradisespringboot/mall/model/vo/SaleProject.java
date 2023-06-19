package tw.idv.petradisespringboot.mall.model.vo;

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

@Table(name = "sale_project")
public class SaleProject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sale_pro_id")
	private Integer saleProId;

	@Column(name = "sale_pro_name")
	private String saleProName;

	@Column(name = "sale_pro_start")
	private String saleProStart;

	@Column(name = "sale_pro_end")
	private String saleProEnd;
	
	@Override
	public String toString() {
		return "SaleProject {" + 
				"saleProId=" + saleProId + 
				"saleProName=" + saleProName + 
				"saleProStart=" + saleProStart + 
				"saleProEnd=" + saleProEnd + 
				'}';
	}

}

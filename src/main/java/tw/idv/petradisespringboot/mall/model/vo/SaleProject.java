package tw.idv.petradisespringboot.mall.model.vo;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	private Date saleProStart;

	@Column(name = "sale_pro_end")
	private Date saleProEnd;
	
	@JsonIgnore
	@OneToMany(mappedBy = "saleProject", cascade = CascadeType.ALL)
	private List<Sale> sale;
	
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

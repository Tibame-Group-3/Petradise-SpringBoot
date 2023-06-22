package tw.idv.petradisespringboot.mall.model.vo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Timestamp saleProStart;

	@Column(name = "sale_pro_end")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Timestamp saleProEnd;
	
	@JsonIgnore
	@OneToMany(mappedBy = "saleProId", cascade = CascadeType.ALL)
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

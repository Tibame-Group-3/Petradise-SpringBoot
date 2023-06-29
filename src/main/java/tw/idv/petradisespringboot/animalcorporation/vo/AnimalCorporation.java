package tw.idv.petradisespringboot.animalcorporation.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="animal_corporation")
public class AnimalCorporation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "corp_id")
	private Integer corpid ;
	
	@Column(name = "applied_status", insertable = false)
	private Character appliedStatus;
	
	@Column(name = "corp_account")
	private String corpAccount;

	@Column(name = "corp_password")
	private String corpPassword;

	@Column(name = "corp_name")
	private String corpName;
	
	@Column(name = "corp_registered_id")
	private String corpRegisteredId;

	@Column(name = "corp_address")
	private String corpAddress;
	
	@Column(name = "contact_name")
	private String contactName;
	
	@Column(name = "contact_phone")
	private String contactPhone;
	
	@Column(name = "contact_email")
	private String contactEmail;
	
	@Column(name = "corp_access", insertable = false)
	private Character corpAccess;
	
	public String toString() {
		
		return "Animal [corpId="+corpid +",appliedStatus="+appliedStatus +",corpAccount="+corpAccount +",corpName="+corpName +"corpRegisteredId=" +corpRegisteredId +"corpAddress=" +corpAddress
				+"contactName" + contactName +"contactPhone=" +contactPhone +",contactEmail="+contactEmail +" corpaccess="+corpAccess+"]";
				
	}
}

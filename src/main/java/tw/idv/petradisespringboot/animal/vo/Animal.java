package tw.idv.petradisespringboot.animal.vo;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tw.idv.petradisespringboot.animlpic.vo.AnimalPic;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Table(name = "animal")
public class Animal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "animal_id")
	private Integer animalid;
	
	
	@Column(name = "corp_id")
	private Integer corpid;
	
	@Column(name = "mem_id")
	private Integer memid;
	
	@Column(name = "type")
	private String animaltype;
	
	@Column(name = "animal_name")
	private String animalname;
	
	@Column(name = "animal_sex")
	private String animalsex;
	
	@Column(name = "animal_age")
	private String animalage;
	
	@Column(name = "animal_pic",columnDefinition = "LONGBLOB")
	private byte[] animalpic;
	
	@Column(name = "status", insertable = false)
	private String aniamlstatus;
	
	@Column(name ="animal_info_note", insertable = false )
	private String animalinfo;
	

	
	public String toString() {
		return "Animal [animalID="+animalid +",corpID="+corpid +",menID="+ memid +",Type="+animaltype +"animalName=" +animalname +"animalStatus=" +aniamlstatus
				+"animalSex" + animalsex +"animalAge=" +animalage +",animalInfonote="+animalinfo + "]";
				
	}



	

	
}
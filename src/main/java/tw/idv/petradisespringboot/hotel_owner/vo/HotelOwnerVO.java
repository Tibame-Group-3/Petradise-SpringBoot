package tw.idv.petradisespringboot.hotel_owner.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "hotel_owner")
public class HotelOwnerVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer hotelId;
	@Column(name = "hotel_name" )
	private String hotelName;
	@Column(name = "hotel_address")
	private String hotelAddress;
	@Column(name = "hotel_status", insertable = false)
	private String hotelStatus;
	@Column(name = "hotel_lic_pic" , insertable = false)
	private byte[] hotelLicPic;
	@Column(name = "owner_account")
	private String ownerAccount;
	@Column(name = "owner_password")
	private String ownerPassword;
	@Column(name = "owner_name")
	private String ownerName;
	@Column(name = "owner_id")
	private String ownerId;
	@Column(name = "owner_bank")
	private String ownerBank;
	@Column(name = "owner_phone")
	private String ownerPhone;
	@Column(name = "owner_email")
	private String ownerEmail;
	@Column(name = "owner_access" , insertable = false)
	private String ownerAccess;
	@Column(name = "review_score_people" , insertable = false, updatable = false)
	private Integer reviewScorePeople;
	@Column(name = "review_score_total" , insertable = false)
	private Integer reviewScoreTotal;
	@Column(name = "hotel_lic_id")
	private String hotelLicId;
	@Transient
	private String imageBase64;
	
	public void setBase64Image(String base64Image) {
		this.imageBase64 = imageBase64;
		
	}
	
	public String getBase64Image() {
		return imageBase64;
	}
	
	

}

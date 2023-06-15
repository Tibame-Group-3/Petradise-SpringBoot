package tw.idv.petradisespringboot.roomType.vo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Table(name ="room_type")
public class RoomType implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_type_id")
	private Integer roomTypeId;

	@Column(name = "hotel_id")
	private Integer hotelId;

	@Column(name = "room_type_name")
	private String roomTypeName;

	@Column(name = "room_type_amount")
	private Integer roomTypeAmount;

	@Column(name = "room_type_sale_status", insertable = false)
	private Character roomTypeSaleStatus;

	@Column(name = "room_type_about")
	private String roomTypeAbout;

	@Column(name = "room_type_price")
	private Integer roomTypePrice;

	@Column(name = "room_pet_type")
	private String roomPetType;

	@Column(name = "room_type_size")
	private Character roomTypeSize;
	
	@JsonIgnore
	@OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL)
	    private List<RoomPic> roomPics;

	@Override
	public String toString() {
		return "RoomType [roomTypeId=" + roomTypeId + ", hotelId=" + hotelId + ", roomTypeName=" + roomTypeName
				+ ", roomTypeAmount=" + roomTypeAmount + ", roomTypeSaleStatus=" + roomTypeSaleStatus
				+ ", roomTypeAbout=" + roomTypeAbout + ", roomTypePrice=" + roomTypePrice + ", roomPetType="
				+ roomPetType + ", roomTypeSize=" + roomTypeSize + "]";
	}

}

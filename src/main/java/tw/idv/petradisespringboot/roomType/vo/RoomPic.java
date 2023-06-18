package tw.idv.petradisespringboot.roomType.vo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Table(name ="room_pic")
public class RoomPic implements Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_pic_id")
	private Integer roomPicId;

	@ManyToOne
	@JoinColumn(name = "room_type_id")
	private RoomType roomType;

	
	@Column(name = "room_pic")
	private Byte[] roomPic;

	
}

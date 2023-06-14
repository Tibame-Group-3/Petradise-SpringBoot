package tw.idv.petradisespringboot.roomType.vo;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name ="room_pic")
public class RoomPic implements Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_pic_id")
	private Integer roomPicId;

	
	@Column(name = "room_type_id")
	private Integer roomTypeId;

	@Lob
	@Column(name = "room_pic")
	private Byte[] roomPic;

}

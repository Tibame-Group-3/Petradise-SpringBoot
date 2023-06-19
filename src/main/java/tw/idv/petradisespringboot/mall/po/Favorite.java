package tw.idv.petradisespringboot.mall.po;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "favorite")
@IdClass(FavoriteCompositePK.class)
public class Favorite {
	
	@Id
	@Column(name = "pd_id")
	private Integer pdId;

	@Id
	@Column(name = "mem_id")
	private Integer memId;
	
	@Column(name = "fav_date")
	private Date favDate;
	
	public String toString() {
		return "Favorite {" +
				"pdId=" + pdId +
				"memId=" + memId +
				"favDate=" + favDate +
				'}';
	}
	
}

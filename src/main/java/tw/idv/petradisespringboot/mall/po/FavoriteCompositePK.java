package tw.idv.petradisespringboot.mall.po;

import java.io.Serializable;
import java.util.Objects;

import lombok.Data;

@Data
public class FavoriteCompositePK implements Serializable {
	private static final long serialVersionUID = -7337370425866004595L;

	private Integer pdId;
	private Integer memId;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FavoriteCompositePK other = (FavoriteCompositePK) obj;
		return Objects.equals(memId, other.memId) && Objects.equals(pdId, other.pdId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(memId, pdId);
	}

}

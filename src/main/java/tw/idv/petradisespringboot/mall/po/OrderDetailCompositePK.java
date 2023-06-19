package tw.idv.petradisespringboot.mall.po;

import java.io.Serializable;
import java.util.Objects;

import lombok.Data;

@Data
public class OrderDetailCompositePK implements Serializable {
	private static final long serialVersionUID = -783590819230352601L;

	private Integer odId;
	private Integer pdId;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetailCompositePK other = (OrderDetailCompositePK) obj;
		return Objects.equals(odId, other.odId) && Objects.equals(pdId, other.pdId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(odId, pdId);
	}

}

package tw.idv.petradisespringboot.mall.model.vo;

import java.io.Serializable;
import java.util.Objects;

import lombok.Data;

@Data
public class SaleCompositePK implements Serializable {
	private static final long serialVersionUID = -1135850127415423523L;

	private Integer pdId;
	private Integer saleProId;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SaleCompositePK other = (SaleCompositePK) obj;
		return Objects.equals(pdId, other.pdId) && Objects.equals(saleProId, other.saleProId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pdId, saleProId);
	}

}

package tw.idv.petradisespringboot.mall.model.dto;

import lombok.Data;

@Data
public class ChangeOrderStatusDTO {
    private Integer odId;
    private Character odStatus;
}

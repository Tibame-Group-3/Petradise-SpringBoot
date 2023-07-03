package tw.idv.petradisespringboot.member.dto;

import lombok.Data;

@Data
public class GetAllDTO {

    private int page;
    private int size;
    private String sort;
    private String order;

    public boolean needsSort() {
        return sort != null && order != null;
    }

    public boolean needsPage() {
        return page != 0 && size != 0;
    }

}

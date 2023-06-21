package tw.idv.petradisespringboot.admin.controller;

import lombok.Data;
import tw.idv.petradisespringboot.admin.vo.Admin;
import tw.idv.petradisespringboot.admin.vo.AdminAccess;

import java.util.List;
@Data
public class AdminDTO {
    private Admin admin;
    private List<AdminAccess> accesses;

    public AdminDTO() {
        admin = new Admin();
    }

}


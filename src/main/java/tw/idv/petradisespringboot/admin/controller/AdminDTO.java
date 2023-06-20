package tw.idv.petradisespringboot.admin.controller;

import lombok.Getter;
import lombok.Setter;
import tw.idv.petradisespringboot.admin.vo.Admin;
import tw.idv.petradisespringboot.admin.vo.AdminAccess;

import java.util.Set;
@Setter
@Getter
public class AdminDTO {
    private Admin admin;
    private Set<AdminAccess> accesses;

    public AdminDTO() {
        admin = new Admin();
    }

}


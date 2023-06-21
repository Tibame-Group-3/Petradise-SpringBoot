package tw.idv.petradisespringboot.admin.controller;

import lombok.Data;
import tw.idv.petradisespringboot.admin.vo.AccessFunction;
import tw.idv.petradisespringboot.admin.vo.Admin;

import java.util.List;
@Data
public class AdminDTO {
    private Admin admin;
    private List<AccessFunction> functions;

    public AdminDTO() {
        admin = new Admin();
    }

}


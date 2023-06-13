package tw.idv.petradisespringboot.admin.controller;

import org.springframework.web.bind.annotation.RestController;
import tw.idv.petradisespringboot.admin.service.AdminService;
@RestController
public class AdminController {

    private final AdminService service;
    public AdminController(AdminService service) {
        this.service = service;
    }






}

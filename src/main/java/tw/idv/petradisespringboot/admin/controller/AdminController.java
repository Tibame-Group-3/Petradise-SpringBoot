package tw.idv.petradisespringboot.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tw.idv.petradisespringboot.admin.service.AdminService;
import tw.idv.petradisespringboot.admin.vo.Admin;

@RestController
public class AdminController {

    private final AdminService service;
    public AdminController(AdminService service) {
        this.service = service;
    }
    @GetMapping
    Admin findById(@PathVariable Integer id){
        return service.findById(id);
    }




}

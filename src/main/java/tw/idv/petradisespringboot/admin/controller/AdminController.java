package tw.idv.petradisespringboot.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tw.idv.petradisespringboot.admin.service.AdminService;
import tw.idv.petradisespringboot.admin.vo.Admin;

import java.util.List;

@RestController
public class AdminController {

    private final AdminService service;
    public AdminController(AdminService service) {
        this.service = service;
    }
//    @PostMapping("admin/login")
//    Admin login(String account, String password){
//        return service.login(account, password);
//    }

    Admin add(String name, String email, Character title){
        return service.addNew(name, email, title);
    }
    @GetMapping("/admin/all")
    List<Admin> getAll(){
        return service.getAllAdmins();
    }

    @GetMapping("/admin/id/{id}")
    Admin findById(@PathVariable Integer id){
        return service.findById(id);
    }




}

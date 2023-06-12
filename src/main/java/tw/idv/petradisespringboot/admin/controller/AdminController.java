package tw.idv.petradisespringboot.admin.controller;

import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.admin.service.AdminService;
import tw.idv.petradisespringboot.admin.vo.Admin;

import java.util.List;

@RestController
public class AdminController {

    private final AdminService service;

    AdminController(AdminService service) {
        this.service = service;
    }

    @GetMapping("/admins/all")
    List<Admin> all() {
        return service.getAll();
    }

    @GetMapping("/admins/id/{id}")
    Admin one(@PathVariable Integer id) {
        return service.findByID(id);
    }

    @PostMapping("/admins/add")
    Admin add(@RequestBody Admin admin) {
        return service.add(admin);
    }
}

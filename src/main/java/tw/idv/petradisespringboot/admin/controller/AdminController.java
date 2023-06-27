package tw.idv.petradisespringboot.admin.controller;

import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.admin.service.AdminService;
import tw.idv.petradisespringboot.admin.vo.Admin;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService service;
    public AdminController(AdminService service) {
        this.service = service;
    }
    @PostMapping("/add")
    public Admin addAdmin(@RequestBody AdminDTO adminDTO) {
        return service.add(adminDTO);
    }
    @PutMapping("/id/{id}/modify")
    Admin modifyAdmin(@PathVariable Integer id, @RequestBody Admin updatedAdmin) {
        return service.modify(id, updatedAdmin);
    }
    @GetMapping("/id/{id}")
    Admin findById(@PathVariable Integer id){
        return service.findById(id);
    }

    @GetMapping("/search/name/{keyword}")
    public List<Admin> searchAdminsByName(@PathVariable String keyword) {
        return service.searchAdminsByName(keyword);
    }
    @GetMapping("/title/{title}")
    List<Admin> findByTitle(@PathVariable char title){
        return service.findAdminsByTitle(title);
    }
    @GetMapping("/status/{status}")
    List<Admin> findByStatus(@PathVariable char status){
        return service.findAdminsByStatus(status);
    }
    @GetMapping("/all")
    List<Admin> getAdminsByIdOrderByTitle(){
        return service.getAdminsByIdOrderByTitle();
    }
    @GetMapping("/all/status/desc")
    List<Admin> getAdminsByIdOrderByStatusDesc(){
        return service.getAdminsByIdOrderByStatusDesc();
    }
    @PutMapping("/id/{id}/change-title")
    Admin changeAdminTitle(@PathVariable Integer id, @RequestBody Map<String, String> requestBody) {
        String newTitle = requestBody.get("title");
        char title = newTitle.charAt(0);
        return service.changeAdminTitle(id, title);
    }
    @PutMapping("/id/{id}/change-status")
    Admin changeAdminStatus(@PathVariable Integer id, @RequestBody Map<String, String> requestBody) {
        String newStatus = requestBody.get("status");
        char status = newStatus.charAt(0);
        return service.changeAdminStatus(id, status);
    }


}

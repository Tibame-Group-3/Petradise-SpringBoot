package tw.idv.petradisespringboot.admin.service.impl;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tw.idv.petradisespringboot.admin.repo.AdminRepository;
import tw.idv.petradisespringboot.admin.service.AdminService;
import tw.idv.petradisespringboot.admin.vo.Admin;

import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository repository;
    AdminServiceImpl(AdminRepository repository){
        this.repository = repository;
    }

    @Override
    public Admin add(Admin admin) {
        return repository.save(admin);
    }
    @Override
    public Admin modify(Integer id, Admin updatedAdmin) {
        return repository.findById(id).map(existingAdmin -> {
            existingAdmin.setName(updatedAdmin.getName());
            existingAdmin.setAccount(updatedAdmin.getAccount());
            existingAdmin.setPassword(updatedAdmin.getPassword());
            existingAdmin.setPhone(updatedAdmin.getPhone());
            existingAdmin.setAddress(updatedAdmin.getAddress());
            existingAdmin.setEmail(updatedAdmin.getEmail());
            return repository.save(existingAdmin);
        }).orElse(null);
    }

    @Override
    public Admin findById(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new AdminNotFoundException(id)
        );
    }
    @Override
    public List<Admin> searchAdminsByName(String keyword) {
        return repository.findByNameContainingIgnoreCase(keyword);
    }

    @Override
    public List<Admin> findAdminsByTitle(char title) {
        return repository.findAdminsByTitle(title);
    }

    @Override
    public List<Admin> findAdminsByStatus(char status) {
        return repository.findAdminsByStatus(status);
    }

    @Override
    public List<Admin> getAdminsByIdOrderByTitle() {
        Sort sortByTitle = Sort.by(Sort.Direction.ASC, "title");
        return repository.findAll(sortByTitle);
    }

    @Override
    public List<Admin> getAdminsByIdOrderByStatusDesc() {
        Sort sortByStatus = Sort.by(Sort.Direction.DESC, "status");
        return repository.findAll(sortByStatus);
    }
    @Override
    public Admin changeAdminTitle(Integer id, char newTitle) {
        return repository.findById(id).map(admin -> {
            admin.setTitle(newTitle);
            return repository.save(admin);
        }).orElse(null);
    }
    @Override
    public Admin changeAdminStatus(Integer id, char newStatus) {
        return repository.findById(id).map(admin -> {
            admin.setStatus(newStatus);
            return repository.save(admin);
        }).orElse(null);
    }



}


package tw.idv.petradisespringboot.admin.service.impl;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tw.idv.petradisespringboot.admin.controller.AdminDTO;
import tw.idv.petradisespringboot.admin.repo.AccessFunctionRepository;
import tw.idv.petradisespringboot.admin.repo.AdminAccessRepository;
import tw.idv.petradisespringboot.admin.repo.AdminRepository;
import tw.idv.petradisespringboot.admin.service.AdminService;
import tw.idv.petradisespringboot.admin.vo.Admin;
import tw.idv.petradisespringboot.admin.vo.AdminAccess;
import tw.idv.petradisespringboot.admin.vo.AdminAccessId;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final AdminAccessRepository adminAccessRepository;
    private final AccessFunctionRepository accessFunctionRepository;

    AdminServiceImpl(AdminRepository adminRepository, AdminAccessRepository adminAccessRepository, AccessFunctionRepository accessFunctionRepository){
        this.adminRepository = adminRepository;
        this.adminAccessRepository = adminAccessRepository;
        this.accessFunctionRepository = accessFunctionRepository;
    }

    @Transactional
    @Override
    public Admin add(AdminDTO dto) {
        var admin = dto.getAdmin();
        // Save the Admin entity first so that it has an ID
        var newAdmin = adminRepository.save(admin);
        var functions = dto.getFunctions();
        if (functions != null) {
            for (var function : functions) {

                if (function != null) {
                    var access = new AdminAccess();
                    // Create the composite key
                    var accessId = new AdminAccessId(newAdmin.getId(), function.getId());
                    access.setId(accessId);
                    access.setAdmin(newAdmin);
                    access.setAccessFunction(function);
                    // Save the AdminAccess entity
                    var newAccess = adminAccessRepository.save(access);
                    var existingAccesses = newAdmin.getAccesses();
                    if (existingAccesses == null) {
                        existingAccesses = new ArrayList<>();
                    }
                    existingAccesses.add(newAccess);
                    newAdmin.setAccesses(existingAccesses);
                }
            }
        }
        return newAdmin;
    }

    @Override
    public Admin modify(Integer id, Admin updatedAdmin) {
        return adminRepository.findById(id).map(existingAdmin -> {
            existingAdmin.setName(updatedAdmin.getName());
            existingAdmin.setAccount(updatedAdmin.getAccount());
            existingAdmin.setPassword(updatedAdmin.getPassword());
            existingAdmin.setPhone(updatedAdmin.getPhone());
            existingAdmin.setAddress(updatedAdmin.getAddress());
            existingAdmin.setEmail(updatedAdmin.getEmail());
            return adminRepository.save(existingAdmin);
        }).orElse(null);
    }

    @Override
    public Admin findById(Integer id) {
        return adminRepository.findById(id).orElseThrow(
                () -> new AdminNotFoundException(id)
        );
    }
    @Override
    public List<Admin> searchAdminsByName(String keyword) {
        return adminRepository.findByNameContainingIgnoreCase(keyword);
    }

    @Override
    public List<Admin> findAdminsByTitle(char title) {
        return adminRepository.findAdminsByTitle(title);
    }

    @Override
    public List<Admin> findAdminsByStatus(char status) {
        return adminRepository.findAdminsByStatus(status);
    }

    @Override
    public List<Admin> getAdminsByIdOrderByTitle() {
        Sort sortByTitle = Sort.by(Sort.Direction.ASC, "title");
        return adminRepository.findAll(sortByTitle);
    }

    @Override
    public List<Admin> getAdminsByIdOrderByStatusDesc() {
        Sort sortByStatus = Sort.by(Sort.Direction.DESC, "status");
        return adminRepository.findAll(sortByStatus);
    }
    @Override
    public Admin changeAdminTitle(Integer id, char newTitle) {
        return adminRepository.findById(id).map(admin -> {
            admin.setTitle(newTitle);
            return adminRepository.save(admin);
        }).orElse(null);
    }
    @Override
    public Admin changeAdminStatus(Integer id, char newStatus) {
        return adminRepository.findById(id).map(admin -> {
            admin.setStatus(newStatus);
            return adminRepository.save(admin);
        }).orElse(null);
    }



}


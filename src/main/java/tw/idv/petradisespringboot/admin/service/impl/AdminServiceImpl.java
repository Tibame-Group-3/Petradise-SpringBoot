package tw.idv.petradisespringboot.admin.service.impl;

import org.springframework.stereotype.Service;
import tw.idv.petradisespringboot.admin.repo.AdminRepository;
import tw.idv.petradisespringboot.admin.service.AdminService;
import tw.idv.petradisespringboot.admin.vo.Admin;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository repository;

    AdminServiceImpl(AdminRepository repository) {
        this.repository = repository;
    }

    @Override
    public Admin findByID(Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new AdminNotFoundException(id));
    }

    @Override
    public Admin add(Admin admin) {
        return repository.save(admin);
    }

    @Override
    public Admin update(Admin newAdmin) {
        return repository.save(newAdmin);
    }

    @Override
    public List<Admin> getAll() {
        return repository.findAll();
    }
}

class AdminNotFoundException extends RuntimeException {

    AdminNotFoundException(Integer id){
        super("Admin not found, id: " + id);
    }
}
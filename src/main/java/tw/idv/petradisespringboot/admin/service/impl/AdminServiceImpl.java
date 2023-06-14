package tw.idv.petradisespringboot.admin.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tw.idv.petradisespringboot.admin.repo.AdminDAO;
import tw.idv.petradisespringboot.admin.service.AdminService;
import tw.idv.petradisespringboot.admin.vo.Admin;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminDAO dao;

    @Override
    public Admin login(String account, String password) {
        return null;
    }

    @Override
    public Admin addNew(String name, String email, Character title) {

        Admin admin = new Admin();
        admin.setAccount("admin" + admin.getId());
        admin.setPassword("pw" + admin.getPassword());
        admin.setName(name);
        admin.setEmail(email);
        admin.setTitle(title);
        return dao.insert(admin);
    }

    @Override
    public Admin modify(String name, String account, String password, String phone, String address, String email) {
        return null;
    }

    @Override
    public Admin modify(String name, String account, String password, String phone, String address, String email, Character title, Character status) {
        return null;
    }

    @Override
    public List<Admin> getAllAdmins() {
        return dao.getAll();
    }

    @Override
    public Admin findById(Integer id) {
        return dao.findById(id);
    }

}

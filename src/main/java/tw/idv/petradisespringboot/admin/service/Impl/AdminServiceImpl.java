package tw.idv.petradisespringboot.admin.service.Impl;

import tw.idv.petradisespringboot.admin.service.AdminService;
import tw.idv.petradisespringboot.admin.vo.Admin;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    @Override
    public Admin login(String account, String password) {
        return null;
    }

    @Override
    public Admin addNew(String name, String email, String title, String access) {
        return null;
    }

    @Override
    public Admin modify(String name, String account, String password, String phone, String address, String email) {
        return null;
    }

    @Override
    public Admin modify(String name, String account, String password, String phone, String address, String email, String title, Character status) {
        return null;
    }

    @Override
    public List<Admin> getAllAdmins() {
        return null;
    }
}

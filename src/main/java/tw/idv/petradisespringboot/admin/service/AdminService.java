package tw.idv.petradisespringboot.admin.service;

import tw.idv.petradisespringboot.admin.vo.Admin;

import java.util.List;

public interface AdminService {

    Admin findById(Integer id);

    List<Admin> getAllAdmins();

    Admin login(String account, String password);

    Admin addNew(String name, String email, Character title);
    Admin modify(String name, String account, String password, String phone, String address, String email);

    Admin modify(String name, String account, String password, String phone, String address, String email, Character title, Character status);

}

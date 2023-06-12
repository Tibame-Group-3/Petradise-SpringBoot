package tw.idv.petradisespringboot.admin.service;

import tw.idv.petradisespringboot.admin.vo.Admin;

import java.util.List;

public interface AdminService {

    Admin login(String account, String password);

    Admin addNew(String name, String email, String title, String access);

    Admin modify(String name, String account, String password, String phone, String address, String email);
    Admin modify(String name, String account, String password, String phone, String address, String email, String title, Character status);

    List<Admin> getAllAdmins();


}

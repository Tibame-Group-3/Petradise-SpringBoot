package tw.idv.petradisespringboot.admin.service;

import tw.idv.petradisespringboot.admin.vo.Admin;

import java.util.List;

public interface AdminService {

    Admin findByID(Integer id);

    Admin add(Admin admin);

    Admin update(Admin newAdmin);

    List<Admin> getAll();

}

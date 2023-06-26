<<<<<<< HEAD
//package tw.idv.petradisespringboot.admin.service;
//
//import tw.idv.petradisespringboot.admin.vo.Admin;
//
//import java.util.List;
//
//public interface AdminService {
//
//    Admin add(Admin admin);
//    Admin modify(Integer id, Admin updatedAdmin);
//    Admin findById(Integer id);
//    List<Admin> searchAdminsByName(String keyword);
//    List<Admin> findAdminsByTitle(char title);
//    List<Admin> findAdminsByStatus(char status);
//    List<Admin> getAdminsByIdOrderByTitle();
//    List<Admin> getAdminsByIdOrderByStatusDesc();
//    Admin changeAdminTitle(Integer id, char newTitle);
//    Admin changeAdminStatus(Integer id, char newStatus);
//
//
//
//}
=======
package tw.idv.petradisespringboot.admin.service;

import tw.idv.petradisespringboot.admin.controller.AdminDTO;
import tw.idv.petradisespringboot.admin.vo.Admin;

import java.util.List;

public interface AdminService {

    Admin add(AdminDTO adminDTO);
    Admin modify(Integer id, Admin updatedAdmin);
    Admin findById(Integer id);
    List<Admin> searchAdminsByName(String keyword);
    List<Admin> findAdminsByTitle(char title);
    List<Admin> findAdminsByStatus(char status);
    List<Admin> getAdminsByIdOrderByTitle();
    List<Admin> getAdminsByIdOrderByStatusDesc();
    Admin changeAdminTitle(Integer id, char newTitle);
    Admin changeAdminStatus(Integer id, char newStatus);




}
>>>>>>> a40ae059860a82bdc1916a1e934939562cee8df6

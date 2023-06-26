<<<<<<< HEAD
//package tw.idv.petradisespringboot.admin.repo;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//import tw.idv.petradisespringboot.admin.vo.Admin;
//
//import java.util.List;
//
//@Repository
//public interface AdminRepository extends JpaRepository <Admin, Integer> {
//
//    List<Admin> findByNameContainingIgnoreCase(String keyword);
//    List<Admin> findAdminsByTitle(char title);
//    List<Admin> findAdminsByStatus(char status);
//}
=======
package tw.idv.petradisespringboot.admin.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.idv.petradisespringboot.admin.vo.Admin;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository <Admin, Integer> {

    List<Admin> findByNameContainingIgnoreCase(String keyword);
    List<Admin> findAdminsByTitle(char title);
    List<Admin> findAdminsByStatus(char status);



}
>>>>>>> a40ae059860a82bdc1916a1e934939562cee8df6

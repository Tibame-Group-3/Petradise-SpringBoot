package tw.idv.petradisespringboot.admin.repo;

import tw.idv.petradisespringboot.admin.vo.Admin;

public interface AdminDAO {

    // 查
    Admin findById(Integer id);

    // 增
    Admin insert(Admin admin);

    // 刪
    Boolean deleteById(Integer id);

    // 改
    Admin update(Admin newAdmin);

}

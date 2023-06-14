package tw.idv.petradisespringboot.adminaccess.repo;

import org.springframework.stereotype.Repository;
import tw.idv.petradisespringboot.adminaccess.vo.AdminAccess;

import java.util.List;

@Repository
public interface AdminAccessRepository {

    List<AdminAccess>  findByAdminId(Integer adminId);

    List<AdminAccess> findByFunctionId(Integer functionId);
}

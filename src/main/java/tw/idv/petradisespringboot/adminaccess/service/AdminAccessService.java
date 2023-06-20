package tw.idv.petradisespringboot.adminaccess.service;

import tw.idv.petradisespringboot.adminaccess.vo.AdminAccess;

public interface AdminAccessService  {

    AdminAccess add(AdminAccess adminAccess);

    Boolean delete(AdminAccess adminAccess);


}

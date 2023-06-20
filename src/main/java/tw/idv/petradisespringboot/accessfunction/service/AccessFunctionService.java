package tw.idv.petradisespringboot.accessfunction.service;

import tw.idv.petradisespringboot.accessfunction.vo.AccessFunction;

public interface AccessFunctionService {

    AccessFunction add(AccessFunction function);

    Boolean delete(Integer id);
}

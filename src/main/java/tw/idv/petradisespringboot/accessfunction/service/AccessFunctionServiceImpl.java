package tw.idv.petradisespringboot.accessfunction.service;

import org.springframework.stereotype.Service;
import tw.idv.petradisespringboot.accessfunction.repo.AccessFunctionRepository;
import tw.idv.petradisespringboot.accessfunction.vo.AccessFunction;
@Service
public class AccessFunctionServiceImpl implements AccessFunctionService {

    private final AccessFunctionRepository repository;

    public AccessFunctionServiceImpl(AccessFunctionRepository repository) {
        this.repository = repository;
    }

    @Override
    public AccessFunction add(AccessFunction function) {
        return repository.save(function);
    }

    @Override
    public Boolean delete(Integer id) {
        return true;
    }

}

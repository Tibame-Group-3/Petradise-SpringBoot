package tw.idv.petradisespringboot.accessfunction.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.idv.petradisespringboot.accessfunction.vo.AccessFunction;

import java.util.List;
@Repository
public interface AccessFunctionRepository extends JpaRepository<AccessFunction, Integer> {

    List<AccessFunction> findByName(Character name);

}

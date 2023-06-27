package tw.idv.petradisespringboot.animal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import tw.idv.petradisespringboot.animal.vo.Animal;



@Component
public interface AnimalRepository extends JpaRepository<Animal, Integer> {



	

	
	
}

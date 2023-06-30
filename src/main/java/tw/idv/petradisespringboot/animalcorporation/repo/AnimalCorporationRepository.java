package tw.idv.petradisespringboot.animalcorporation.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import tw.idv.petradisespringboot.animalcorporation.vo.AnimalCorporation;
import java.util.List;


@Component
public interface AnimalCorporationRepository extends JpaRepository<AnimalCorporation, Integer>{
	
	List<AnimalCorporation> findByAppliedStatusNot(Character appliedStatus);
	
	List<AnimalCorporation> findByAppliedStatus(Character appliedStatus);

}

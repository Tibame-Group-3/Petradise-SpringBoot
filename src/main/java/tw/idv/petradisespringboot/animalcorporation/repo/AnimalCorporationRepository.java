package tw.idv.petradisespringboot.animalcorporation.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import tw.idv.petradisespringboot.animalcorporation.vo.AnimalCorporation;

@Component
public interface AnimalCorporationRepository extends JpaRepository<AnimalCorporation, Integer>{

}

package tw.idv.petradisespringboot.animalcorporation.service;

import java.util.List;


import tw.idv.petradisespringboot.animalcorporation.vo.AnimalCorporation;


public interface AnimalCorporationService {

	AnimalCorporation add(AnimalCorporation animalCorporation);
	
	AnimalCorporation update(AnimalCorporation animalCorporation);
	
	List<AnimalCorporation> findAllWithStatusNo1();
	
	AnimalCorporation findByID(Integer id);
	
	AnimalCorporation updateByCorpAccess(AnimalCorporation animalCorporation);

	List<AnimalCorporation> findByStatus0();
}

package tw.idv.petradisespringboot.animalcorporation.service;

import tw.idv.petradisespringboot.animalcorporation.vo.AnimalCorporation;

import java.util.List;


public interface AnimalCorporationService {

	AnimalCorporation add(AnimalCorporation animalCorporation);
	
	AnimalCorporation update(AnimalCorporation animalCorporation);
	
	List<AnimalCorporation> findAll();
	
	AnimalCorporation findByID(Integer id);
	
	AnimalCorporation updateByCorpAccess(AnimalCorporation animalCorporation);

	AnimalCorporation login(String account, String password);

}

package tw.idv.petradisespringboot.animalcorporation.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import tw.idv.petradisespringboot.animalcorporation.repo.AnimalCorporationRepository;
import tw.idv.petradisespringboot.animalcorporation.service.AnimalCorporationService;
import tw.idv.petradisespringboot.animalcorporation.vo.AnimalCorporation;

@Service
public class AnimalCorporationImpl implements AnimalCorporationService{
	
	private AnimalCorporationRepository animalCorporationRepository;
	
	

	public AnimalCorporationImpl(AnimalCorporationRepository animalCorporationRepository) {
		this.animalCorporationRepository = animalCorporationRepository;
	}

	@Override
	public AnimalCorporation add(AnimalCorporation animalCorporation) {
		return animalCorporationRepository.save(animalCorporation);
	}

	@Override
	public AnimalCorporation update(AnimalCorporation editAnimalCorporation) {
		return animalCorporationRepository.save(editAnimalCorporation);
	}

	@Override
	public List<AnimalCorporation> findAll() {
		return animalCorporationRepository.findAll();
	}

	@Override
	public AnimalCorporation findByID(Integer id) {
		return animalCorporationRepository.findById(id).orElseThrow();
	}

	@Override
	public AnimalCorporation updateByCorpAccess(AnimalCorporation animalCorporation) {

		Character currentCorpAccess = animalCorporation.getCorpAccess();

	    if (currentCorpAccess.equals('1')) {
	        animalCorporation.setCorpAccess('0');
	    } else if (currentCorpAccess.equals('0')) {
	        animalCorporation.setCorpAccess('1');
	    }
		return animalCorporationRepository.save(animalCorporation);
	}

}

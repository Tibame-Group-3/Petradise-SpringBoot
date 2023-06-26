package tw.idv.petradisespringboot.animal.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import tw.idv.petradisespringboot.animal.repo.AnimalRepository;
import tw.idv.petradisespringboot.animal.service.AnimalService;
import tw.idv.petradisespringboot.animal.vo.Animal;
import tw.idv.petradisespringboot.lostpetarticle.vo.LostPetArticle;

@Service
public class AnimalServiceImpl implements AnimalService {

	private final AnimalRepository repository;

	AnimalServiceImpl(AnimalRepository repository) {
		this.repository = repository;

	}

	@Override
	public List<Animal> findAllAnimal() {

		return repository.findAll();
	}

	@Override
	public Animal findAnimalById(Integer id) {

		return repository.findById(id).orElseThrow(() -> new AnimalNotFoundException(id));
	}

	@Override
	public Animal save(Animal animal) {
		return repository.save(animal);
	}
	

	@Override
	public Animal addAnimal(Animal animal) {

		return repository.save(animal);
	}


	


}

class AnimalNotFoundException extends RuntimeException {
	AnimalNotFoundException(Integer id) {
		super("找不到會員ID: " + id);
	}
}

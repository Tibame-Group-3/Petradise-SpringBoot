package tw.idv.petradisespringboot.animal.service;

import java.util.List;

import tw.idv.petradisespringboot.animal.vo.Animal;

public interface AnimalService {

	Animal findAnimalById(Integer id);

	Animal save(Animal animal);

	List<Animal> findAllAnimal();
	
	Animal addAnimal(Animal animal);



	


}

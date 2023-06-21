package tw.idv.petradisespringboot.animal.contrllor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import tw.idv.petradisespringboot.animal.service.AnimalService;
import tw.idv.petradisespringboot.animal.vo.Animal;


@RestController

public class AnimalController {

	@Autowired
	private AnimalService service;

	@GetMapping("/animals/all")
	List<Animal> all() {
		return service.findAllAnimal();
	}

	@PostMapping("/animals/save")
	Animal newAnimal(@RequestBody Animal animal) {
		return service.save(animal);
	}

	@GetMapping("/animals/id/{id}")
	Animal one(@PathVariable Integer id) {
		return service.findAnimalById(id);
	}

	

}

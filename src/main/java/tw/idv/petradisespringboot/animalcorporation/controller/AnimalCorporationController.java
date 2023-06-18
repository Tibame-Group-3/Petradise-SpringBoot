package tw.idv.petradisespringboot.animalcorporation.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.petradisespringboot.animalcorporation.service.AnimalCorporationService;
import tw.idv.petradisespringboot.animalcorporation.vo.AnimalCorporation;

@RestController
public class AnimalCorporationController {
	
	private AnimalCorporationService service;
	
	AnimalCorporationController(AnimalCorporationService service){
		this.service = service;
	}
	
	@GetMapping("/animalcorporation/all")
	List<AnimalCorporation> all(){
		return service.findAll();
	}
	
	@PostMapping("/animalcorporation/update")
	AnimalCorporation update(@RequestBody AnimalCorporation editAnimalCorporation) {
		System.out.println(editAnimalCorporation);
		return service.update(editAnimalCorporation);
	}
	
	@PostMapping("/animalcorporation/apply")
	AnimalCorporation apply(@RequestBody AnimalCorporation animalCorporation) {
		return service.add(animalCorporation);
	}
}

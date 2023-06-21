package tw.idv.petradisespringboot.animalapplication.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.petradisespringboot.animalapplication.service.AdoptedApplicationService;
import tw.idv.petradisespringboot.animalapplication.vo.AdoptedApplication;





@RestController
public class AdoptedApplicationController {
	
	private final AdoptedApplicationService service;

	public AdoptedApplicationController(AdoptedApplicationService service) {
		this.service = service;
	}
	

	@GetMapping("/adopted_applications/all")
	List<AdoptedApplication> all() {
		
		return service.getAllAdoptedApplication();
	}

	@PostMapping("/adopted_applications/save")
	AdoptedApplication newAdoptedApplication(@RequestBody AdoptedApplication adoptedApplication) {
		return service.save(adoptedApplication);
	}

	@GetMapping("/adopted_applications/id/{id}")
	AdoptedApplication one(@PathVariable Integer id) {
		return service.findAdoptedApplicationById(id);
	}
	
	
	
		
	
}








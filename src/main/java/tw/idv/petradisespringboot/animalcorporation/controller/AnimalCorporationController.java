package tw.idv.petradisespringboot.animalcorporation.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.petradisespringboot.animalcorporation.service.AnimalCorporationService;
import tw.idv.petradisespringboot.animalcorporation.vo.AnimalCorporation;

@RestController
@RequestMapping("/animalCorporation")
public class AnimalCorporationController {
	
	private AnimalCorporationService service;
	
	AnimalCorporationController(AnimalCorporationService service){
		this.service = service;
	}
	
	@GetMapping("/all")
	ResponseEntity<List<AnimalCorporation>> allWithout1(){
		List<AnimalCorporation> animalCorporations = service.findAllWithStatusNo1();
		
		return ResponseEntity.ok(animalCorporations);
	}
	
	@GetMapping("/all/withDefault")
	ResponseEntity<List<AnimalCorporation>> allWithDefault(){
		List<AnimalCorporation> animalCorporations = service.findByStatus0();
		
		return ResponseEntity.ok(animalCorporations);
	}
	
	@PostMapping("/update")
	AnimalCorporation update(@RequestBody AnimalCorporation editAnimalCorporation) {
		return service.update(editAnimalCorporation);
	}
	
	@PostMapping("/apply")
	AnimalCorporation apply(@RequestBody AnimalCorporation animalCorporation) {
		return service.add(animalCorporation);
	}
	
	@ResponseBody
	@GetMapping("/id={id}")
	AnimalCorporation  findionById(@PathVariable Integer id) {
	    return service.findByID(id);
	}
	
	@PutMapping("/id={id}/editCorpAccess")
	ResponseEntity<AnimalCorporation> editCorpAccess(@PathVariable Integer id){
		AnimalCorporation corporation = service.findByID(id);
		
		if (corporation == null) {
			return ResponseEntity.notFound().build();
		}
		service.updateByCorpAccess(corporation);
		return ResponseEntity.ok(corporation);
	}
	
	@PostMapping("/status/corpId={corpId}")
	ResponseEntity<?> editAppliedStatus(@RequestParam("appliedStatus") Character appliedStatus, @PathVariable Integer corpId ){
		
		AnimalCorporation animalCorporation = service.findByID(corpId);
		
		if (animalCorporation == null) {
			return ResponseEntity.notFound().build();
		}
		
		animalCorporation.setAppliedStatus(appliedStatus);
		service.update(animalCorporation);
		
		return ResponseEntity.ok("success !");
	}
	
	
}

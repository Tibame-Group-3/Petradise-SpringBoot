package tw.idv.petradisespringboot.animalcorporation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.animalcorporation.dto.LoginDTO;
import tw.idv.petradisespringboot.animalcorporation.service.AnimalCorporationService;
import tw.idv.petradisespringboot.animalcorporation.vo.AnimalCorporation;

import java.util.List;

@RestController
@RequestMapping("/animalCorporation")
public class AnimalCorporationController {
	
	private AnimalCorporationService service;
	
	AnimalCorporationController(AnimalCorporationService service){
		this.service = service;
	}
	
	@GetMapping("/all")
	List<AnimalCorporation> all(){
		return service.findAll();
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
	
	@PutMapping("/id={id}/updateStatus")
	ResponseEntity<AnimalCorporation> editStatus(@PathVariable Integer id){
		AnimalCorporation corporation = service.findByID(id);
		
		if (corporation == null) {
			return ResponseEntity.notFound().build();
		}
		service.updateByCorpAccess(corporation);
		return ResponseEntity.ok(corporation);
		
	}

	@PostMapping("/login")
	ResponseEntity<?> login(@RequestBody LoginDTO dto) {
		try {
			var vo = service.login(dto.getAccount(), dto.getPassword());
			return ResponseEntity.ok(vo);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}

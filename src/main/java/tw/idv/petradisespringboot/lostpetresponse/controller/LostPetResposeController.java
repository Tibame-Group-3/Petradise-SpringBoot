package tw.idv.petradisespringboot.lostpetresponse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.petradisespringboot.lostpetresponse.service.LostPetResponseService;
import tw.idv.petradisespringboot.lostpetresponse.vo.LostPetResponse;

@RestController
public class LostPetResposeController {
	
	private LostPetResponseService lostPetResponseService;

	public LostPetResposeController(LostPetResponseService lostPetResponseService) {
		this.lostPetResponseService = lostPetResponseService;
	}
	
	@PostMapping("/LostPetRespose/add")
	LostPetResponse create(@RequestBody LostPetResponse lostPetResponse) {
		System.out.println(lostPetResponse);
		return lostPetResponseService.add(lostPetResponse);
	}
	
	@GetMapping("/LostPetRespose/id={id}")
	LostPetResponse getById(@PathVariable Integer id) {
		return lostPetResponseService.findById(id);
	}
}

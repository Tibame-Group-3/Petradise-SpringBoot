package tw.idv.petradisespringboot.lostpetresponse.controller;

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
	
	@PostMapping("/LostPetArticle/add")
	LostPetResponse create(@RequestBody LostPetResponse lostPetResponse) {
		System.out.println(lostPetResponse);
		return lostPetResponseService.add(lostPetResponse);
	}
	
	
}

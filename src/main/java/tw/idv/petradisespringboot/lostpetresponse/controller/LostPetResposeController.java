package tw.idv.petradisespringboot.lostpetresponse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.petradisespringboot.lostpetarticle.repo.LostPetArticleRepository;
import tw.idv.petradisespringboot.lostpetresponse.service.LostPetResponseService;
import tw.idv.petradisespringboot.lostpetresponse.vo.LostPetResponse;

@RestController
public class LostPetResposeController {
	

    private final LostPetResponseService responseService;
   

	public LostPetResposeController(LostPetResponseService responseService) {
		super();
		this.responseService = responseService;
	}

	@PostMapping("/LostPetRespose/add")
	LostPetResponse create(@RequestBody LostPetResponseDTO lostPetResponseDTO) {
		System.out.println(lostPetResponseDTO);
		return responseService.add(lostPetResponseDTO);
	}
	
	@GetMapping("/LostPetRespose/id={id}")
	LostPetResponse getById(@PathVariable Integer id) {
		return responseService.findById(id);
	}
}

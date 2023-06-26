package tw.idv.petradisespringboot.lostpetresponse.controller;

import org.springframework.web.bind.annotation.*;
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
<<<<<<< HEAD
		System.out.println(lostPetResponseDTO);
=======
>>>>>>> origin/main
		return responseService.add(lostPetResponseDTO);
	}
	
	@GetMapping("/LostPetRespose/id={id}")
	LostPetResponse getById(@PathVariable Integer id) {
		return responseService.findById(id);
	}
}

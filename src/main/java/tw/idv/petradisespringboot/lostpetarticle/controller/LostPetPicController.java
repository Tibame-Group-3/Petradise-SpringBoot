package tw.idv.petradisespringboot.lostpetarticle.controller;


import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.servlet.http.Part;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.petradisespringboot.lostpetarticle.repo.LostPetPicRepo;
import tw.idv.petradisespringboot.lostpetarticle.service.LostPetArticleService;
import tw.idv.petradisespringboot.lostpetarticle.vo.LostPetPic;
import tw.idv.petradisespringboot.lostpetarticle.vo.LostPetPicSTR;
import tw.idv.petradisespringboot.pet.vo.PetPic;
import tw.idv.petradisespringboot.pet.vo.PetPicRequestVO;

@RestController
@RequestMapping("/LostPetPic")
public class LostPetPicController {

	private LostPetArticleService lostPetArticleService;
	
    public LostPetPicController(LostPetArticleService lostPetArticleService) {
		this.lostPetArticleService = lostPetArticleService;
	}



	@PostMapping("/create")
    ResponseEntity<String> add(@RequestBody LostPetPicSTR lostPetPicSTR) {
        try {
            var petPic = new LostPetPic();
            petPic.setLostPetPicId(lostPetPicSTR.getLostPetId());
            petPic.setLostPetPic(Base64.getDecoder().decode(lostPetPicSTR.getPic()));
            lostPetArticleService.addLostPetPic(petPic);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload pet pic.");
        }
    }
//    public ResponseEntity<String> create(@RequestParam("file") Part file) throws IOException {
//        LostPetPic lostPetPic = new LostPetPic();
//        InputStream inputStream = file.getInputStream();
//        byte[] fileBytes = inputStream.readAllBytes();
//        lostPetPic.setLostPetPic(fileBytes);
//        LostPetPic savedPic = lostPetPicRepo.save(lostPetPic);
//
//        if (savedPic != null) {
//            return ResponseEntity.ok("LostPetPic created successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create LostPetPic");
//        }
//    }


}


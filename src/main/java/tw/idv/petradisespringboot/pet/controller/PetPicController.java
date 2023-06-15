package tw.idv.petradisespringboot.pet.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.pet.service.PetService;
import tw.idv.petradisespringboot.pet.vo.PetPic;
import tw.idv.petradisespringboot.pet.vo.PetPicRequestVO;

import java.util.Base64;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pet-pic")
public class PetPicController {

    private final PetService service;

    PetPicController(PetService service) {
        this.service = service;
    }

    @DeleteMapping("/id={picId}")
    ResponseEntity<?> deletePetPic(@PathVariable Integer picId) {
        try {
            service.deletePetPic(picId);
            return ResponseEntity.ok("Delete pet pic id: " + picId + " success.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete pet pic id: " + picId);
        }
    }

    @PostMapping("/upload")
    ResponseEntity<String> uploadPetPic(@RequestBody PetPicRequestVO vo) {
        try {
            // 將PetPicRequestVO 轉為真正輸入資料庫的VO PetPic
            var petPic = new PetPic();
            petPic.setPetId(vo.getPetId());
            petPic.setPic(Base64.getDecoder().decode(vo.getPic()));
            service.addPetPic(petPic);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload pet pic.");
        }
    }

    @GetMapping("/getByPetId={petId}")
    ResponseEntity<?> getPetPicsByPetId(@PathVariable Integer petId) {
        try {
            var petPics = service.getPetPicsByPetId(petId);
            var petPicVOs = petPics.stream().map(petPic -> {
                var vo = new PetPicRequestVO();
                vo.setId(petPic.getId());
                vo.setPetId(petPic.getPetId());
                vo.setPic(Base64.getEncoder().encodeToString(petPic.getPic()));
                return vo;
            }).collect(Collectors.toList());
            return ResponseEntity
                    .ok(petPicVOs);
        } catch(Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to get pet pics by pet id: " + petId);
        }

    }
}

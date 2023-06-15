package tw.idv.petradisespringboot.pet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.pet.service.PetService;
import tw.idv.petradisespringboot.pet.vo.Pet;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService service;

    public PetController(PetService service) {
        this.service = service;
    }

    @PostMapping("/add")
    ResponseEntity<Pet> newPet(@RequestBody Pet pet) {
        return ResponseEntity.ok(service.addPet(pet));
    }

    @PostMapping("/update")
    ResponseEntity<?> updatePet(@RequestBody Pet pet) {
        return ResponseEntity.ok(pet);
    }

    @GetMapping("/id/{id}")
    ResponseEntity<?> one(@PathVariable Integer id) {
        var pet = service.getPetById(id);
        if (pet.isPresent()) {
            return ResponseEntity.ok(pet.get());
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new PetNotFoundException(id));
    }

    @GetMapping("/member-id/{memberId}")
    ResponseEntity<?> getPetsByMemberId(@PathVariable Integer memberId) {
        return ResponseEntity.ok(service.getPetsByMemId(memberId));
    }

    @DeleteMapping("/pet-pic/{picId}")
    ResponseEntity<?> deletePetPic(@PathVariable Integer picId) {
        try {
            service.deletePetPic(picId);
            return ResponseEntity.ok("Delete pet pic id: " + picId + " success.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete pet pic id: " + picId);
        }
    }

    @PostMapping("/pet-pic/upload")
    ResponseEntity<String> uploadPetPic(@RequestBody String base64) {
        return ResponseEntity.ok("success");
    }
}

class PetNotFoundException extends RuntimeException {

    PetNotFoundException(Integer id) {
        super("找不到寵物ID: " + id);
    }

}
package tw.idv.petradisespringboot.pet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tw.idv.petradisespringboot.pet.repo.PetRepository;
import tw.idv.petradisespringboot.pet.vo.Pet;

@RestController
public class PetController {

    private final PetRepository repository;

    public PetController(PetRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/pets")
    @ResponseBody
    List<Pet> all() {
        return repository.findAll();
    }

    @PostMapping("/pets")
    Pet newPet(@RequestBody Pet pet) {
        return repository.save(pet);
    }

    @GetMapping("/pets/{id}")
    Pet one(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow(() -> new PetNotFoundException(id));
    }
}

class PetNotFoundException extends RuntimeException {

    PetNotFoundException(Integer id) {
        super("找不到寵物ID: " + id);
    }

}
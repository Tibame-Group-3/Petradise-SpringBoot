package tw.idv.petradisespringboot.pet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.petradisespringboot.pet.repo.PetRepository;
import tw.idv.petradisespringboot.pet.vo.Pet;

@RestController
@RequestMapping("pet")
public class PetController {

    @Autowired
    private PetRepository repository;

    @GetMapping(path = "/all")
    @ResponseBody
    List<Pet> getAll() {
        return repository.findAll();
    }

    @PostMapping(path = "/add")
    Pet addPet(@RequestBody Pet pet) {
        return repository.save(pet);
    }
}

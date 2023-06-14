package tw.idv.petradisespringboot.pet.service;

import tw.idv.petradisespringboot.pet.vo.Pet;

import java.util.List;
import java.util.Optional;

public interface PetService {

    List<Pet> getPetsByMemId(Integer memId);

    Optional<Pet> getPetById(Integer id);

    Pet addPet(Pet pet);

    Pet updatePet(Pet pet);
}

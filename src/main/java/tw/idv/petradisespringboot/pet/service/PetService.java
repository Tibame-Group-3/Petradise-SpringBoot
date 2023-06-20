package tw.idv.petradisespringboot.pet.service;

import tw.idv.petradisespringboot.pet.vo.Pet;
import tw.idv.petradisespringboot.pet.vo.PetPic;

import java.util.List;
import java.util.Optional;

public interface PetService {

    List<Pet> getAll();

    List<Pet> getPetsByMemId(Integer memId);

    Optional<Pet> getPetById(Integer id);

    Pet addPet(Pet pet);

    Pet updatePet(Pet pet);

    void addPetPic(PetPic petPic);

    void deletePetPic(Integer picId);

    List<PetPic> getPetPicsByPetId(Integer petId);
}

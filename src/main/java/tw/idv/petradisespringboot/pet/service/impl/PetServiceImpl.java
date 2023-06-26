package tw.idv.petradisespringboot.pet.service.impl;

import org.springframework.stereotype.Service;
import tw.idv.petradisespringboot.pet.repo.PetRepository;
import tw.idv.petradisespringboot.pet.service.PetService;
import tw.idv.petradisespringboot.pet.vo.Pet;

import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;


    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }


    @Override
    public List<Pet> getAll() {
        return petRepository.findAll();
    }

    @Override
    public List<Pet> getPetsByMemId(Integer memId) {
        return petRepository.findByMemberId(memId);
    }

    @Override
    public Optional<Pet> getPetById(Integer id) {
        return petRepository.findById(id);
    }

    @Override
    public Pet addPet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Pet updatePet(Pet pet) {
        if (!petRepository.existsById(pet.getId())) {
            return pet;
        }
        return petRepository.save(pet);
    }

}

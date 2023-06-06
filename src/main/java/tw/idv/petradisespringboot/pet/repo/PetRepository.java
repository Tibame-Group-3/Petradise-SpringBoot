package tw.idv.petradisespringboot.pet.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.idv.petradisespringboot.pet.vo.Pet;

public interface PetRepository extends JpaRepository<Pet, Integer> {

}

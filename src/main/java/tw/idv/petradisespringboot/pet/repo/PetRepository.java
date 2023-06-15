package tw.idv.petradisespringboot.pet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.idv.petradisespringboot.pet.vo.Pet;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {

    List<Pet> findByMemID(Integer memId);
}

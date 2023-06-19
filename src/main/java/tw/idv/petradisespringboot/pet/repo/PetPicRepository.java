package tw.idv.petradisespringboot.pet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.idv.petradisespringboot.pet.vo.PetPic;

import java.util.List;

@Repository
public interface PetPicRepository extends JpaRepository<PetPic, Integer> {

    List<PetPic> findByPetId(Integer petId);

 }

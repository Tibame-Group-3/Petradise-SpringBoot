package tw.idv.petradisespringboot.lostpetresponse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import tw.idv.petradisespringboot.lostpetresponse.vo.LostPetResponse;

@Component
public interface LostPetResponseRepo extends JpaRepository<LostPetResponse, Integer>{

}

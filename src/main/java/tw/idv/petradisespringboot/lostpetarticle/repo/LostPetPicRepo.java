package tw.idv.petradisespringboot.lostpetarticle.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import tw.idv.petradisespringboot.lostpetarticle.vo.LostPetPic;

@Component
public interface LostPetPicRepo extends JpaRepository<LostPetPic, Integer>{

}

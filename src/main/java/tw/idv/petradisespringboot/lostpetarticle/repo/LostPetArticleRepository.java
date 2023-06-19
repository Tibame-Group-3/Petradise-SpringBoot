package tw.idv.petradisespringboot.lostpetarticle.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import tw.idv.petradisespringboot.lostpetarticle.vo.LostPetArticle;

@Component
public interface LostPetArticleRepository extends JpaRepository<LostPetArticle, Integer>{

	
}

package tw.idv.petradisespringboot.lostpetarticle.service;

import java.util.List;
import java.util.Optional;

import tw.idv.petradisespringboot.lostpetarticle.vo.LostPetArticle;
import tw.idv.petradisespringboot.lostpetarticle.vo.LostPetPic;

public interface LostPetArticleService {
	
	List<LostPetArticle> getAllArticles();
	
	LostPetArticle findById(Integer id);
	
	LostPetArticle add(LostPetArticle lostPetArticle);
	
	LostPetArticle update(LostPetArticle lostPetArticle);
	
	void delete(Integer id);
	
	List<LostPetArticle> findBySpecies(String species);
	
	List<LostPetArticle> findByLostPlace(String lostPlace);
	
	LostPetArticle update4Status(LostPetArticle lostPetArticle);

}

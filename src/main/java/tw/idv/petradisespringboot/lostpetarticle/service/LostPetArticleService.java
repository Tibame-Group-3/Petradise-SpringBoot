package tw.idv.petradisespringboot.lostpetarticle.service;

import java.util.List;

import tw.idv.petradisespringboot.lostpetarticle.vo.LostPetArticle;

public interface LostPetArticleService {
	
	List<LostPetArticle> getAllArticles();
	
	LostPetArticle findById(Integer id);
	
	LostPetArticle add(LostPetArticle lostPetArticle);
	
	LostPetArticle update(LostPetArticle lostPetArticle);
	
	LostPetArticle delete(Integer id);
	
	

}

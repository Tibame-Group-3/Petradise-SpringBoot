package tw.idv.petradisespringboot.lostpetarticle.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import tw.idv.petradisespringboot.lostpetarticle.repo.LostPetArticleRepository;
import tw.idv.petradisespringboot.lostpetarticle.service.LostPetArticleService;
import tw.idv.petradisespringboot.lostpetarticle.vo.LostPetArticle;

@Service
public class LostPetArticleServiceImpl implements LostPetArticleService{
	
	
	private LostPetArticleRepository lostPetArticleRepository;	

	public LostPetArticleServiceImpl(LostPetArticleRepository lostPetArticleRepository) {
		this.lostPetArticleRepository = lostPetArticleRepository;
	}

	@Override
	public List<LostPetArticle> getAllArticles() {
	    List<LostPetArticle> articles = lostPetArticleRepository.findAll();

	    List<LostPetArticle> filteredArticles = articles.stream()
	            .filter(article -> "0".equals(article.getArticleStatus()))
	            .collect(Collectors.toList());

	    return filteredArticles;
	}

	@Override
	public LostPetArticle add(LostPetArticle lostPetArticle) {

		return lostPetArticleRepository.save(lostPetArticle);
	}

	@Override
	public LostPetArticle update(LostPetArticle lostPetArticle) {

		return lostPetArticleRepository.save(lostPetArticle);
	}

	@Override
	public void delete(Integer id) {
		lostPetArticleRepository.deleteById(id);
		
	}

	@Override
	public List<LostPetArticle> findBySpecies(String species) {
		
		return lostPetArticleRepository.findBySpecies(species);
	}

	@Override
	public List<LostPetArticle> findByLostPlace(String lostPlace) {
		return lostPetArticleRepository.findByLostPlace(lostPlace);
	}

	@Override
	public LostPetArticle findById(Integer id) {
		return lostPetArticleRepository.findById(id).orElseThrow();
	}

	

}

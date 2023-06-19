package tw.idv.petradisespringboot.lostpetarticle.service.impl;

import java.util.List;

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
		return lostPetArticleRepository.findAll();
	}

	@Override
	public LostPetArticle findById(Integer id) {
		return lostPetArticleRepository.findById(id).orElseThrow();
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
	public LostPetArticle delete(Integer id) {
		lostPetArticleRepository.deleteById(id);
		return null;
	}
	

}

package tw.idv.petradisespringboot.lostpetarticle.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import tw.idv.petradisespringboot.lostpetarticle.repo.LostPetArticleRepository;
import tw.idv.petradisespringboot.lostpetarticle.repo.LostPetPicRepo;
import tw.idv.petradisespringboot.lostpetarticle.service.LostPetArticleService;
import tw.idv.petradisespringboot.lostpetarticle.vo.LostPetArticle;
import tw.idv.petradisespringboot.lostpetarticle.vo.LostPetPic;

@Service
public class LostPetArticleServiceImpl implements LostPetArticleService{
	
	
	private LostPetArticleRepository lostPetArticleRepository;
	
	private LostPetPicRepo lostPetPicRepo;
	
	public LostPetArticleServiceImpl(LostPetArticleRepository lostPetArticleRepository, LostPetPicRepo lostPetPicRepo) {
		this.lostPetArticleRepository = lostPetArticleRepository;
		this.lostPetPicRepo = lostPetPicRepo;
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
	public void delete(Integer id) {
		lostPetArticleRepository.deleteById(id);
		
	}

	@Override
	public void addLostPetPic(LostPetPic lostPetPic) {
		lostPetPicRepo.save(lostPetPic);
	}
	

}

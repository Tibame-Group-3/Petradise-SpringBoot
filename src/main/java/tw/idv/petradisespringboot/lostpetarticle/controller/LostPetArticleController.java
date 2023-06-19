package tw.idv.petradisespringboot.lostpetarticle.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.petradisespringboot.lostpetarticle.service.LostPetArticleService;
import tw.idv.petradisespringboot.lostpetarticle.vo.LostPetArticle;

@RestController
public class LostPetArticleController {
	
	
	private LostPetArticleService lostPetArticleService;
	
	public LostPetArticleController(LostPetArticleService lostPetArticleService) {
		this.lostPetArticleService = lostPetArticleService;
	}
	
	@GetMapping("/LostPetArticle")
	List<LostPetArticle> getAllArticle(){
		var kk = lostPetArticleService.getAllArticles();
		System.out.println(kk);
		return lostPetArticleService.getAllArticles();
	}
		
	@GetMapping("/LostPetArticle/id/{id}")
	LostPetArticle getArticle(@PathVariable Integer id) {
		return lostPetArticleService.findById(id);
	}
	
	@PostMapping("/LostPetArticle/create")
	LostPetArticle create(@RequestBody LostPetArticle lostPetArticle) {
		System.out.println(lostPetArticle);
		return lostPetArticleService.add(lostPetArticle);
	}
	
	@PostMapping("/LostPetArticle/update")
	LostPetArticle update(@RequestBody LostPetArticle lostPetArticle) {
		System.out.println(lostPetArticle);
		return lostPetArticleService.update(lostPetArticle);
		
	}
}

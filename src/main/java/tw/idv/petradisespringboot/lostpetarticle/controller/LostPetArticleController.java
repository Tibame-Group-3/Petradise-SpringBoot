package tw.idv.petradisespringboot.lostpetarticle.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.petradisespringboot.lostpetarticle.service.LostPetArticleService;
import tw.idv.petradisespringboot.lostpetarticle.vo.LostPetArticle;

@RestController
@RequestMapping("/LostPetArticle")
public class LostPetArticleController {
	
	
	private LostPetArticleService lostPetArticleService;
	
	public LostPetArticleController(LostPetArticleService lostPetArticleService) {
		this.lostPetArticleService = lostPetArticleService;
	}
	
	@GetMapping("/all")
	List<LostPetArticle> getAllArticle(){
		
		var kk = lostPetArticleService.getAllArticles();
		System.out.println(kk);
		return lostPetArticleService.getAllArticles();
	}
	
	@GetMapping("/species={species}")
	List<LostPetArticle> getArticleBySpecies(@PathVariable String species){
		if (species == "狗") {
			return lostPetArticleService.findBySpecies(species);	
		} else if ( species == "貓"){
			return lostPetArticleService.findBySpecies(species);
		} else if ( species == "鳥") {
			return lostPetArticleService.findBySpecies(species);
		} else if ( species == "其他"){
			return lostPetArticleService.findBySpecies(species);
		}
		return null;
	}
		
	@GetMapping("/id={id}")
	LostPetArticle getArticle(@PathVariable Integer id) {
	 
		 return lostPetArticleService.findById(id);
	}
	
	@PostMapping("/create")
	LostPetArticle create(@RequestBody LostPetArticle lostPetArticle) {
		System.out.println(lostPetArticle);
		return lostPetArticleService.add(lostPetArticle);
	}
	
	@PostMapping("/update")
	LostPetArticle update(@RequestBody LostPetArticle lostPetArticle) {
		System.out.println(lostPetArticle);
		return lostPetArticleService.update(lostPetArticle);
		
	}
}

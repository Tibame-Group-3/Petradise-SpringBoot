package tw.idv.petradisespringboot.lostpetarticle.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@ResponseBody
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
	
	@PutMapping("/id={id}/delete")
	public ResponseEntity<LostPetArticle> editStatus(@PathVariable Integer id) {
	    LostPetArticle article = lostPetArticleService.findById(id);

	    if (article == null) {
	        return ResponseEntity.notFound().build();
	    }

	    article.setArticleStatus("1");

	    lostPetArticleService.update(article);

	    return ResponseEntity.ok(article);
	}

}

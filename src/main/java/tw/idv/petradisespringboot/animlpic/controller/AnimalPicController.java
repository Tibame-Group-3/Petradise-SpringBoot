package tw.idv.petradisespringboot.animlpic.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.petradisespringboot.animlpic.service.AnimalPicService;
import tw.idv.petradisespringboot.animlpic.vo.AnimalPic;

@RestController
public class AnimalPicController {

	private  AnimalPicService service;

	@GetMapping("/animal_pics/all")
	@ResponseBody
	List<AnimalPic> all() {
		return service.getAllAnimalpic();
	}

	@PostMapping("/animal_pics/save")
	AnimalPic newAnimal_pic(@RequestBody AnimalPic animal_pic) {
		return service.save(animal_pic);
	}

	@GetMapping("/animal_pics/id/{id}")
	AnimalPic one(@PathVariable Integer id) {
		return service.findAnimalpicById(id);

	}
}

class Animal_picNotFoundException extends RuntimeException {

	Animal_picNotFoundException(Integer id) {
		super("找不到寵物ID: " + id);
	}

}

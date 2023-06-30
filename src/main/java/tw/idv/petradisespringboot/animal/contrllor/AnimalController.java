package tw.idv.petradisespringboot.animal.contrllor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.petradisespringboot.animal.service.AnimalService;
import tw.idv.petradisespringboot.animal.vo.Animal;
import com.google.gson.Gson;

@RestController
@RequestMapping("/animals")
public class AnimalController {

	@Autowired
	private AnimalService service;

	@GetMapping("/all")
	List<Animal> all() {
		List<Animal> list = service.findAllAnimal();

		for (Animal animal : list) {
			System.out.println(animal.getAnimalpic());
		}
		return list;
	}

	@ResponseBody
	@GetMapping("/{id}")
	Animal findAnimalById(@PathVariable Integer id) {
		return service.findAnimalById(id);
	}

	// 新增
	@PostMapping("/save")
	public Animal saveAnimal(@RequestBody Animal animal) {
		return service.addAnimal(animal);
	}

//	@PostMapping("/create")
//	public Animal createAnimal(@RequestBody Animal animal) {
//		return service.addAnimal(animal);
//	}

	// 更新动物信息
	@PostMapping("/update/{id}")
	public Animal updateAnimal(@PathVariable Integer id, @RequestBody Animal animal) {
		// 先通过ID查找要更新的动物
		Animal existingAnimal = service.findAnimalById(id);
		if (existingAnimal != null) {
			// 更新动物的相关属性
			existingAnimal.setAnimalname(animal.getAnimalname());
			existingAnimal.setAnimalage(animal.getAnimalage());
			existingAnimal.setAnimalsex(animal.getAnimalsex());

			// 调用service保存更新后的动物
			return service.save(existingAnimal);
		} else {
			// 如果找不到对应ID的动物，可以根据实际情况返回适当的响应或错误处理
			// 这里只是简单地返回null
			return null;
		}
	}

//	@GetMapping("/search")
//	public List<Animal> searchAnimals(@RequestParam(value = "animaltype", required = false) String animalType,
//			@RequestParam(value = "animalsex", required = false) String animalSex,
//			@RequestParam(value = "animalage", required = false) String animalAge) {
//		System.out.println(animalType);
//		// 动态查询
//		Specification<Animal> spec = Specification.where(null);
//	    if (animalType != null) {
//	        spec = spec.and(AnimalSpecifications.hasAnimalType(animalType));
//	    }
//	    if (animalSex != null) {
//	        spec = spec.and(AnimalSpecifications.hasAnimalSex(animalSex));
//	    }
//	    if (animalAge != null) {
//	        spec = spec.and(AnimalSpecifications.hasAnimalAge(animalAge));
//	    }

//		System.out.println(service.searchAnimal("狗", "公", "5歲"));
////	    System.out.println(service.searchAnimal(animalType, animalSex, animalAge));
//		// 调用service方法进行查询
//		return service.searchAnimal(animalType, animalSex, animalAge);
//	}

	@GetMapping("/search")
	public List<Animal> searchByAnimalTypeSex(@RequestParam("animaltype") String animaltype ,@RequestParam("animalsex")String animalsex ) {
		System.out.println("種類: " + animaltype + animalsex  );
		List<Animal> animals = service.findAnimalByAnimaltypeAndAnimalsex(animaltype, animalsex);
		System.out.println("列表: "+animals);

		for (Animal animal : animals) {
			System.out.println(animal.getAnimalpic());
		}
		return animals;
	}

}
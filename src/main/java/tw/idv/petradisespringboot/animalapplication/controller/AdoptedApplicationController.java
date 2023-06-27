package tw.idv.petradisespringboot.animalapplication.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.petradisespringboot.animalapplication.service.AdoptedApplicationService;
import tw.idv.petradisespringboot.animalapplication.vo.AdoptedApplication;




@RequestMapping("/adopted_applications")
@RestController
public class AdoptedApplicationController {
	
	private final AdoptedApplicationService service;

	public AdoptedApplicationController(AdoptedApplicationService service) {
		this.service = service;
	}
	

	@GetMapping("/all")
	List<AdoptedApplication> all() {
		
		return service.getAllAdoptedApplication();
	}

	@PostMapping("/save")
	AdoptedApplication newAdoptedApplication(@RequestBody AdoptedApplication adoptedApplication) {
		return service.addAdoptedApplication(adoptedApplication);
	}

	@ResponseBody
	@GetMapping("{id}")
	AdoptedApplication findAdoptedApplicationById(@PathVariable Integer id) {
		return service.findAdoptedApplicationById(id);
	}
	
	
	@PostMapping("/update/{id}")
	public AdoptedApplication updateAdoptedApplication(@PathVariable Integer id, @RequestBody AdoptedApplication updatedApplication) {
	    // 先通过ID查找要更新的认养申请
	    AdoptedApplication existingApplication = service.findAdoptedApplicationById(id);
	    if (existingApplication != null) {
	        // 更新认养申请的相关属性
	 	      existingApplication.setAnimalId(updatedApplication.getAnimalId());
	        
	        // 调用service保存更新后的认养申请
	        return service.addAdoptedApplication(existingApplication);
	    } else {
	        // 如果找不到对应ID的认养申请，可以根据实际情况返回适当的响应或错误处理
	        // 这里只是简单地返回null
	        return null;
	    }
	}
	



		
	
}








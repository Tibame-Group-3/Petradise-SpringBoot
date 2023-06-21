package tw.idv.petradisespringboot.mall.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.mall.model.vo.SaleProject;
import tw.idv.petradisespringboot.mall.service.SaleProjectService;

@RestController
@RequestMapping("/saleProject")
public class SaleProjectController {

	private final SaleProjectService saleProjectService;

	SaleProjectController(SaleProjectService saleProjectService) {
		this.saleProjectService = saleProjectService;
	}

	@GetMapping("/{id}")
	SaleProject getSaleProject(@PathVariable Integer SaleProjectId) {
		return saleProjectService.getSaleProjectById(SaleProjectId);
	}

	@PostMapping("/add")
	SaleProject addSaleProject(@RequestBody SaleProject saleProject) {
		System.out.println(saleProject);
		return saleProjectService.addSaleProject(saleProject);
	}
	
	@PostMapping("/update")
	SaleProject updateSaleProject(@RequestBody SaleProject saleProject) {
		return saleProjectService.updateSaleProject(saleProject);
	}

	@GetMapping("/all")
	List<SaleProject> getAllSaleProjects() {
		var allSaleProject = saleProjectService.getAllSaleProject();
		System.out.println(allSaleProject);
		return saleProjectService.getAllSaleProject();
	}
	
}

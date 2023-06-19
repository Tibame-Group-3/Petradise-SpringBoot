package tw.idv.petradisespringboot.mall.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.mall.model.vo.SaleProject;
import tw.idv.petradisespringboot.mall.service.SaleProjectService;

@RestController
public class SaleProjectController {

	private final SaleProjectService saleProjectService;

	SaleProjectController(SaleProjectService saleProjectService) {
		this.saleProjectService = saleProjectService;
	}

	@GetMapping("/saleProjects/id/{id}")
	SaleProject one(@PathVariable Integer id) {
		return saleProjectService.findById(id);
	}

	@PostMapping("/saleProjects/add")
	SaleProject addSaleProject(@RequestBody SaleProject saleProject) {
		return saleProjectService.add(saleProject);
	}

	@GetMapping("/saleProjects/all")
	List<SaleProject> all() {
		return saleProjectService.findAll();
	}

}

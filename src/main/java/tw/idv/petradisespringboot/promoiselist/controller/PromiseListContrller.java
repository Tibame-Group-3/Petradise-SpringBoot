package tw.idv.petradisespringboot.promoiselist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import tw.idv.petradisespringboot.promoiselist.service.PromiseListService;
import tw.idv.petradisespringboot.promoiselist.vo.PromiseList;


@RestController
public class PromiseListContrller {

	@Autowired
	private  PromiseListService service;

	

	@GetMapping("/promise_lists/all")
	List<PromiseList> all() {
		return service.findAllPromiselist();
	}

	@PostMapping("/promise_lists/save")
	PromiseList newPromise_list(@RequestBody PromiseList promise_list) {
		return service.save(promise_list);
	}

	@GetMapping("/promise_lists/id/{id}")
	PromiseList one(@PathVariable Integer id) {
		return service.findPromiselistById(id);
	}
	
	
}

class promise_listNotFoundException extends RuntimeException {

	promise_listNotFoundException(Integer id) {
		super("找不到寵物ID: " + id);
	}

}

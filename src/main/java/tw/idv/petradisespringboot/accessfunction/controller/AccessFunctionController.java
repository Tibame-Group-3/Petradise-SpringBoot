package tw.idv.petradisespringboot.accessfunction.controller;

import org.springframework.web.bind.annotation.RestController;
import tw.idv.petradisespringboot.accessfunction.service.AccessFunctionService;

@RestController
public class AccessFunctionController {

    private final AccessFunctionService service;

    public AccessFunctionController(AccessFunctionService service) {
        this.service = service;
    }

}

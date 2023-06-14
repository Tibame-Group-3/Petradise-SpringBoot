package tw.idv.petradisespringboot.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.member.service.MemberService;
import tw.idv.petradisespringboot.member.vo.Member;

import java.util.List;

@RestController
class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping("/members/all")
    ResponseEntity<?> all() {
        return new ResponseEntity<List<Member>>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping("/members/sign-up")
    Member signUp(@RequestBody Member member) {
        return service.signUp(member);
    }

    @GetMapping("/members/id/{id}")
    Member one(@PathVariable Integer id) {
        return service.findMemberById(id);
    }

    @GetMapping("/members/account/{account}")
    Member findByAccount(@PathVariable String account) { return service.findMemberByAccount(account); }

    @GetMapping("/members/email/{email}")
    Member findByEmail(@PathVariable String email) { return service.findMemberByEmail(email); }
}


class MemberNotFoundException extends RuntimeException {
    MemberNotFoundException(Integer id) {
        super("找不到會員ID: " + id);
    }
}


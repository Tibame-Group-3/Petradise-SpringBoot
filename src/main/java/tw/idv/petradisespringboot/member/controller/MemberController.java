package tw.idv.petradisespringboot.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.member.service.MemberService;
import tw.idv.petradisespringboot.member.vo.Member;

@RestController
@RequestMapping("/members")
class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping("/all")
    ResponseEntity<?> all() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/sign-up")
    ResponseEntity<?> signUp(@RequestBody Member member) {
        return ResponseEntity.ok(service.signUp(member));
    }

    @GetMapping("/id={id}")
    ResponseEntity<?> one(@PathVariable Integer id) {
        var member = service.getById(id);
        if (member.isPresent()) {
            return ResponseEntity.ok(member.get());
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new MemberNotFoundException(id));
    }

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody Member member) {
        var account = member.getAccount();
        var password = member.getPassword();
        var foundMember = service.login(account, password);
        if (foundMember.isPresent()) {
            return ResponseEntity.ok(foundMember.get());
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new MemberNotFoundException(account));
    }
}


class MemberNotFoundException extends RuntimeException {
    MemberNotFoundException(Integer id) {
        super("找不到會員ID: " + id);
    }

    MemberNotFoundException(String account) { super("帳號為 " + account + " 之會員不存在"); }
}


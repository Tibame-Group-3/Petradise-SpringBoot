package tw.idv.petradisespringboot.member.controller;

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
    List<Member> all() {
        return service.getAllMembers();
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


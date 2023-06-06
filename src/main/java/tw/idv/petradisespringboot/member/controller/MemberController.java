package tw.idv.petradisespringboot.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tw.idv.petradisespringboot.member.repo.MemberRepository;
import tw.idv.petradisespringboot.member.service.MemberService;
import tw.idv.petradisespringboot.member.vo.Member;

@RestController
class MemberController {

    private MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping("/members/all")
    List<Member> all() {
        return service.getAllMembers();
    }

    @PostMapping("/members/signUp")
    Member signUp(@RequestBody Member member) {
        return service.signUp(member);
    }

    @GetMapping("/members/id/{id}")
    Member one(@PathVariable Integer id) {
        return service.findMemberById(id);
    }

    @GetMapping("/members/account/{account}")
    Member findByAccount(@PathVariable String account) { return service.findMemberByAccount(account); }
}


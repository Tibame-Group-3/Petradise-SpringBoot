package tw.idv.petradisespringboot.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tw.idv.petradisespringboot.member.repo.MemberRepository;
import tw.idv.petradisespringboot.member.service.MemberService;
import tw.idv.petradisespringboot.member.vo.Member;

@RestController
class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping("/members/all")
    @ResponseBody
    List<Member> all() {
        return service.getAllMembers();
    }

    @PostMapping("/members/signUp")
    @ResponseBody
    Member signUp(@RequestBody Member member) {
        return service.signUp(member);
    }

    @GetMapping("/members/id/{id}")
    @ResponseBody
    Member one(@PathVariable Integer id) {
        return service.findMemberById(id);
    }

    @GetMapping("/members/account/{account}")
    @ResponseBody
    Member findByAccount(@PathVariable String account) { return service.findMemberByAccount(account); }

    @GetMapping("/members/email/{email}")
    @ResponseBody
    Member findByEmail(@PathVariable String email) { return service.findMemberByEmail(email); }
}


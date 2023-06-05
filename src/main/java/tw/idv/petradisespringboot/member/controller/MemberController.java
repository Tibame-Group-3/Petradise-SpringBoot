package tw.idv.petradisespringboot.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.petradisespringboot.member.repo.MemberRepository;
import tw.idv.petradisespringboot.member.vo.Member;

@RestController
@RequestMapping("member")
class MemberController {

	@Autowired
	private MemberRepository repository;

	@GetMapping(path = "/all")
	@ResponseBody
	List<Member> getAllMembers() {
		return repository.findAll();
	}

	@PostMapping(path = "/add")
	Member addMember(@RequestBody Member member) {
		System.out.println(member);
		return repository.save(member);
	}
}

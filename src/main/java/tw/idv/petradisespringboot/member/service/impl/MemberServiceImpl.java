package tw.idv.petradisespringboot.member.service.impl;

import org.springframework.stereotype.Service;
import tw.idv.petradisespringboot.member.repo.MemberRepository;
import tw.idv.petradisespringboot.member.service.MemberService;
import tw.idv.petradisespringboot.member.vo.Member;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository repository;

    MemberServiceImpl(MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public Member login(String account, String password) {
        return null;
    }

    @Override
    public Member signUp(Member newMember) {
        return null;
    }

    @Override
    public List<Member> getAllMembers() {
        return repository.findAll();
    }

    @Override
    public Member findMemberById(Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
    }

    @Override
    public Member update(Member newMember) {
        return repository.save(newMember);
    }

    @Override
    public Member findMemberByAccount(String account) {
        return repository.findByAccount(account);
    }

    @Override
    public Member findMemberByEmail(String email) {
        return repository.findByEmail(email);
    }
}

class MemberNotFoundException extends RuntimeException {
    MemberNotFoundException(Integer id) {
        super("找不到會員ID: " + id);
    }
}


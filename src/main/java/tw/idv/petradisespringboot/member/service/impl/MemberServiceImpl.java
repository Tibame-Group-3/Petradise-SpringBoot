package tw.idv.petradisespringboot.member.service.impl;

import org.springframework.stereotype.Service;
import tw.idv.petradisespringboot.member.repo.MemberRepository;
import tw.idv.petradisespringboot.member.service.MemberService;
import tw.idv.petradisespringboot.member.vo.Member;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository repository;

    MemberServiceImpl(MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Member> login(String account, String password) {
        return null;
    }

    @Override
    public Member signUp(Member newMember) {
        return repository.save(newMember);
    }

    @Override
    public List<Member> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Member> findMemberById(Integer id) {
        return repository
                .findById(id);
    }

    @Override
    public Member update(Member newMember) {
        return repository.save(newMember);
    }

}


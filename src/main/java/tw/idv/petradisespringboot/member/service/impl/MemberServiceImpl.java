package tw.idv.petradisespringboot.member.service.impl;

import org.springframework.stereotype.Service;
import tw.idv.petradisespringboot.member.exceptions.AccountAlreadyExistsException;
import tw.idv.petradisespringboot.member.repo.MemberRepository;
import tw.idv.petradisespringboot.member.service.MemberService;
import tw.idv.petradisespringboot.member.vo.Member;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository repository;

    MemberServiceImpl(MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Member> login(String account, String password) {
        return repository.findByAccountAndPassword(account, password);
    }

    @Override
    public Member signUp(Member newMember) {
        if (repository.findByAccount(newMember.getAccount()).isPresent()) {
            throw new AccountAlreadyExistsException("Account already exists: " + newMember.getAccount());
        }
        return repository.save(newMember);
    }

    @Override
    public List<Member> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Member> getById(Integer id) {
        return repository
                .findById(id);
    }

    @Override
    public Member update(Member newMember) {
        return repository.save(newMember);
    }

    @Override
    public boolean changePassword(Integer id, String oldPassword, String newPassword) {

        var optionalMember = repository.findById(id);
        if (optionalMember.isEmpty()) {
            return false;
        }
       var member = optionalMember.get();
        if (Objects.equals(member.getPassword(), oldPassword)) {
            member.setPassword(newPassword);
            repository.save(member);
            return true;
        }
        return false;
    }

}


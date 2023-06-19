package tw.idv.petradisespringboot.member.service;

import tw.idv.petradisespringboot.member.vo.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Optional<Member> login(String account, String password);

    Member signUp(Member newMember);

    List<Member> getAll();

    Optional<Member> getById(Integer id);

    Member update(Member newMember);
}

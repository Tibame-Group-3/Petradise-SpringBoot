package tw.idv.petradisespringboot.member.service;

import tw.idv.petradisespringboot.member.vo.Member;

import java.util.List;

public interface MemberService {
    Member login(String account, String password);

    Member signUp(Member newMember);

    List<Member> getAllMembers();

    Member findMemberById(Integer id);

    Member update(Member newMember);

    Member findMemberByAccount(String account);

    Member findMemberByEmail(String email);
}

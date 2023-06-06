package tw.idv.petradisespringboot.member.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import tw.idv.petradisespringboot.member.vo.Member;

@Component
public interface MemberRepository extends JpaRepository<Member, Integer> {

    Member findByMemAccount(String account);

    Member findByMemName(String name);

    Member findByMemEmail(String email);

}

package tw.idv.petradisespringboot.member.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import tw.idv.petradisespringboot.member.vo.Member;

@Component
public interface MemberRepository extends JpaRepository<Member, Integer> {

}

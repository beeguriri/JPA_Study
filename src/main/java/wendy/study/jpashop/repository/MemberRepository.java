package wendy.study.jpashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wendy.study.jpashop.model.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByName(String name);
}

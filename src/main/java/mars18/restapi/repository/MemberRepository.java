package mars18.restapi.repository;

import mars18.restapi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmailAndPw(String email, String pw);

    Boolean existsByEmail(String email);

    Boolean existsByName(String name);


}

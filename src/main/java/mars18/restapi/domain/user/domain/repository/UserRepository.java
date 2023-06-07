package mars18.restapi.domain.user.domain.repository;

import mars18.restapi.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);
    Boolean existsByPw(String pw);
    Boolean existsByName(String name);
    User findNameByEmail(String email);

    User findByName(String name);
}

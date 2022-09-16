package mars18.restapi.repository;

import mars18.restapi.dto.UserLoginDto;
import mars18.restapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);
    Boolean existsByPw(String pw);
    Boolean existsByName(String name);
    User findNameByEmail(String email);
}

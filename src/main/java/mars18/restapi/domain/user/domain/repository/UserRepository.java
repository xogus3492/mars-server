package mars18.restapi.domain.user.domain.repository;

import mars18.restapi.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);
    Boolean existsByName(String name);
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
}

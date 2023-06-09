package mars18.restapi.domain.license.domain.repository;

import mars18.restapi.domain.license.domain.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LicenseRepository extends JpaRepository<License, Long> {

    Boolean existsByName(String name);
    Optional<License> findByName(String name);
}

package mars18.restapi.repository;

import mars18.restapi.entity.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebLicenseRepository extends JpaRepository<License, Long> {

    Boolean existsByName(String name);

    License findByName(String name);
}

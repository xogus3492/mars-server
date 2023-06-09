package mars18.restapi.domain.playrecord.domain.repository;

import mars18.restapi.domain.playrecord.domain.PlayRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayRecordRepository extends JpaRepository<PlayRecord, Long> {
    Optional<PlayRecord> findTopByNameOrderByIdDesc(String name);
    List<PlayRecord> findAllByNameOrderByIdDesc(String name);
    List<PlayRecord> findAllByName(String name);
}

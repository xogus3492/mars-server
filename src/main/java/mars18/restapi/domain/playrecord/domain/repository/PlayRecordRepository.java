package mars18.restapi.domain.playrecord.domain.repository;

import mars18.restapi.domain.playrecord.domain.PlayRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayRecordRepository extends JpaRepository<PlayRecord, Long> {
    Boolean existsNameByNameOrderByIdDesc(String name);
    PlayRecord findTopByNameOrderByIdDesc(String name);

    //자격증 서비스
    Boolean existsByNameAndKindAndScoreGreaterThanEqual(String name, String kind, int n);

     //앱 - 마이페이지 서비스
    List<PlayRecord> findByNameOrderByScoreDesc(String name);
    List<PlayRecord> findByName(String name);

}

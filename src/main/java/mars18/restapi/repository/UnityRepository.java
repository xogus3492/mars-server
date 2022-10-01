package mars18.restapi.repository;

import mars18.restapi.dto.UnityDto;
import mars18.restapi.entity.PlayRecord;
import mars18.restapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnityRepository extends JpaRepository<PlayRecord, Long> {
    Boolean existsNameByNameOrderByIdDesc(String name);
    PlayRecord findTopByNameOrderByIdDesc(String name);

    //자격증 서비스
    Boolean existsByNameAndKindAndScoreGreaterThanEqual(String name, String kind, int n);

     //앱 - 마이페이지 서비스
    List<PlayRecord> findByNameOrderByScoreDesc(String name);
    List<PlayRecord> findByName(String name);

}

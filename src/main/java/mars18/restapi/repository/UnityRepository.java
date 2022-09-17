package mars18.restapi.repository;

import mars18.restapi.entity.PlayRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnityRepository extends JpaRepository<PlayRecord, Long> {
    Boolean existsNameByNameOrderByIdDesc(String name);
    PlayRecord findNameByNameOrderByIdDesc(String name);
    PlayRecord findScoreByNameOrderByIdDesc(String name);
    PlayRecord findKindByNameOrderByIdDesc(String name);

    //자격증 서비스
     Boolean existsByNameAndKindAndScoreGreaterThanEqual(String name, String kind, int n);

}

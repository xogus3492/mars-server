package mars18.restapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars18.restapi.repository.UnityRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class RankingService {

    private final UnityRepository unityRepository;
    @Transactional
    public List<Map<Object, Object>> rankingData() {
        //FEEDBACKTAP_VALIDATION(request);

        List<Map<Object, Object>> list = new ArrayList<>();

        System.out.println(unityRepository.count() + "개");
        for(int i = 0; i < unityRepository.count(); i++) {
            Map<Object, Object> map = new HashMap<>();
            map.put("ranking", i + 1);
            map.put("name", unityRepository.findAll(Sort.by(Sort.Direction.DESC, "score")).get(i).getName());
            map.put("kind", unityRepository.findAll(Sort.by(Sort.Direction.DESC, "score")).get(i).getKind());
            map.put("score", unityRepository.findAll(Sort.by(Sort.Direction.DESC, "score")).get(i).getScore());
            map.put("play_at", unityRepository.findAll(Sort.by(Sort.Direction.DESC, "score")).get(i).getCreateAt());

            list.add(map);
        }

        return list;
    }
}

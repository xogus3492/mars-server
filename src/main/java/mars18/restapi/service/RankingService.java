package mars18.restapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars18.restapi.dto.AppFeedbackDto;
import mars18.restapi.exception.CustomException;
import mars18.restapi.repository.UnityRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static mars18.restapi.exception.CustomErrorCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class RankingService {

    private final UnityRepository unityRepository;
    @Transactional
    public List<Map<Object, Object>> rankingData() {
        RANKING_VALIDATION();

        List<Map<Object, Object>> list = new ArrayList<>();

        System.out.println(unityRepository.count() + "개");
        for(int i = 0; i < unityRepository.count(); i++) {
            Map<Object, Object> map = new HashMap<>();
            map.put("ranking", i + 1);
            map.put("name", unityRepository.findAll(Sort.by(Sort.Direction.DESC, "score")).get(i).getName());
            map.put("kind", unityRepository.findAll(Sort.by(Sort.Direction.DESC, "score")).get(i).getKind());
            map.put("score", unityRepository.findAll(Sort.by(Sort.Direction.DESC, "score")).get(i).getScore());
            map.put("playing_time", unityRepository.findAll(Sort.by(Sort.Direction.DESC, "score")).get(i).getPlaying_time());
            map.put("play_at", unityRepository.findAll(Sort.by(Sort.Direction.DESC, "score")).get(i).getCreateAt());

            list.add(map);
        }

        return list;
    }

    // 예외 처리

    private void RANKING_VALIDATION() {
        if (unityRepository.count() == 0)
            throw new CustomException(NOT_EXISTS_PLAY_RECORD); // 플레이 기록이 없음
    }
}

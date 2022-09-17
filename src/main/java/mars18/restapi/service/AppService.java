package mars18.restapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars18.restapi.dto.AppFeedbackDto;
import mars18.restapi.dto.AppMyDto;
import mars18.restapi.dto.UnityDto;
import mars18.restapi.dto.UserLoginDto;
import mars18.restapi.entity.PlayRecord;
import mars18.restapi.exception.CustomException;
import mars18.restapi.repository.UnityRepository;
import mars18.restapi.repository.WebLicenseRepository;
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
public class AppService {

    private final UnityRepository unityRepository;

    private final WebLicenseRepository webLicenseRepository;

    @Transactional
    public AppFeedbackDto.Response feedbackTapData(AppFeedbackDto.Request request) {
        FEEDBACKTAP_VALIDATION(request);

        return AppFeedbackDto.Response.response(
                PlayRecord.builder()
                        .name(unityRepository.findNameByNameOrderByIdDesc(request.getName()).getName())
                        .kind(unityRepository.findKindByNameOrderByIdDesc(request.getName()).getKind())
                        .score(unityRepository.findScoreByNameOrderByIdDesc(request.getName()).getScore())
                        .build()
        );
    }

    @Transactional
    public List<Object> myPageTapData(AppMyDto.Request request) {
        //MYPAGETAP_VALIDATION(request);

        List<Object> parentList = new ArrayList<>();
        List<Map<Object, Object>> childList = new ArrayList<>();
        Map<Object, Object> noArrMap = new HashMap<>();

        noArrMap.put("name", request.getName());
        noArrMap.put("bartender", webLicenseRepository.findByName(request.getName()).getBartender());
        noArrMap.put("baker", webLicenseRepository.findByName(request.getName()).getBaker());

        // kind, ranking, score, play_at, playing_time

        List<PlayRecord> recordData = unityRepository.findByNameOrderByScoreDesc(request.getName());
        // 점수 내림차순 정렬한 name과 같은 이름의 레코드(들)

        System.out.println("recordData.size() = " + recordData.size());
        for(int i = 0; i < recordData.size(); i++) {
            Map<Object, Object> arrMap = new HashMap<>();
            arrMap.put("kind", recordData.get(i).getKind());
            arrMap.put("ranking", i + 1);
            arrMap.put("score", recordData.get(i).getScore());
            arrMap.put("play_at", recordData.get(i).getCreateAt());
            arrMap.put("playing_time", recordData.get(i).getPlaying_time());

            childList.add(arrMap);
        }

        parentList.add(noArrMap);
        parentList.add(childList);

        return parentList;
    }

    // 예외 처리

    private void FEEDBACKTAP_VALIDATION(AppFeedbackDto.Request request) {
        if (request.getName() == null)
        throw new CustomException(NULL_USER_NAME); // 이름 비었을 때 (처리 안되는 이유?)

        if (!(unityRepository.existsNameByNameOrderByIdDesc(request.getName())))
            throw new CustomException(NOT_EXISTS_USER_RECORD); // 유저 이름이 없을 때
    }

    private void MYPAGETAP_VALIDATION(AppMyDto.Request request) {
        if (request.getName() == null)
            throw new CustomException(NULL_USER_NAME); // 이름 비었을 때 (처리 안되는 이유?)

        if (!(unityRepository.existsNameByNameOrderByIdDesc(request.getName())))
            throw new CustomException(NOT_EXISTS_USER_RECORD); // 유저 이름이 없을 때
    }
}

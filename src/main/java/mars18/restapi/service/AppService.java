package mars18.restapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars18.restapi.dto.AppFeedbackDto;
import mars18.restapi.dto.UnityDto;
import mars18.restapi.dto.UserLoginDto;
import mars18.restapi.entity.PlayRecord;
import mars18.restapi.exception.CustomException;
import mars18.restapi.repository.UnityRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static mars18.restapi.exception.CustomErrorCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppService {

    private final UnityRepository unityRepository;

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

    // 예외 처리

    private void FEEDBACKTAP_VALIDATION(AppFeedbackDto.Request request) {
        if (request.getName() == null)
        throw new CustomException(NULL_USER_NAME); // 이름 비었을 때 (처리 안되는 이유?)

        if (!(unityRepository.existsNameByNameOrderByIdDesc(request.getName())))
            throw new CustomException(NO_EXISTS_USER_NAME); // 유저 이름이 없을 때
    }
}

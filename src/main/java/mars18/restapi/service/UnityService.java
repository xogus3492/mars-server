package mars18.restapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars18.restapi.dto.UnityDto;
import mars18.restapi.entity.PlayRecord;
import mars18.restapi.exception.CustomException;
import mars18.restapi.repository.UnityRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.constant.Constable;

import static mars18.restapi.exception.CustomErrorCode.REGISTER_INFO_NULL;
import static mars18.restapi.model.StatusTrue.RECORD_STATUS_TRUE;

@Service
@RequiredArgsConstructor
@Slf4j
public class UnityService {

    private final UnityRepository unityRepository;

    @Transactional
    public Constable playRecord(UnityDto.Request request) {
        PLAYRECORD_VALIDATION(request);

        unityRepository.save(
                PlayRecord.builder()
                        .name(request.getName())
                        .score(request.getScore())
                        .playing_time(request.getPlaying_time())
                        .kind(request.getKind())
                        .build()
        );

        return RECORD_STATUS_TRUE;
    }

    //예외처리

    private void PLAYRECORD_VALIDATION(UnityDto.Request request) {
        if (request.getName() == null)
            throw new CustomException(REGISTER_INFO_NULL); // 이름 비었을 때
    }
}

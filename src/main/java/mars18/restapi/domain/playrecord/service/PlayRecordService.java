package mars18.restapi.domain.playrecord.service;

import lombok.RequiredArgsConstructor;
import mars18.restapi.domain.playrecord.domain.PlayRecord;
import mars18.restapi.domain.playrecord.domain.repository.PlayRecordRepository;
import mars18.restapi.domain.playrecord.dto.FeedbackRequest;
import mars18.restapi.domain.playrecord.dto.FeedbackResponse;
import mars18.restapi.global.exception.CustomErrorCode;
import mars18.restapi.global.exception.CustomException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayRecordService {

    private final PlayRecordRepository playRecordRepository;

    @Transactional(readOnly = true)
    public FeedbackResponse getFeedBack(FeedbackRequest request) {
        PlayRecord playRecord = playRecordRepository.findTopByNameOrderByIdDesc(request.getName())
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_EXISTS_USER_RECORD));

        return FeedbackResponse.of(playRecord);
    }
}

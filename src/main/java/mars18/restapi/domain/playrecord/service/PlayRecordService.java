package mars18.restapi.domain.playrecord.service;

import lombok.RequiredArgsConstructor;
import mars18.restapi.domain.license.domain.License;
import mars18.restapi.domain.license.domain.repository.LicenseRepository;
import mars18.restapi.domain.playrecord.domain.PlayRecord;
import mars18.restapi.domain.playrecord.domain.repository.PlayRecordRepository;
import mars18.restapi.domain.playrecord.dto.FeedbackReadRequest;
import mars18.restapi.domain.playrecord.dto.FeedbackReadResponse;
import mars18.restapi.domain.playrecord.dto.RecordSaveRequest;
import mars18.restapi.domain.playrecord.dto.RecordSaveResponse;
import mars18.restapi.dto.UnityDto;
import mars18.restapi.global.exception.CustomErrorCode;
import mars18.restapi.global.exception.CustomException;
import mars18.restapi.global.policy.LicensePolicy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayRecordService {

    private final PlayRecordRepository playRecordRepository;
    private final LicenseRepository licenseRepository;

    public RecordSaveResponse saveRecord(RecordSaveRequest request) {
        PlayRecord playRecord = playRecordRepository.save(request.toEntity());

        if (!licenseRepository.existsByName(playRecord.getName())) {
            licenseRepository.save(License.toEntity(playRecord));
        }

        License license = licenseRepository.findByName(playRecord.getName())
                .orElseThrow(() -> new CustomException(CustomErrorCode.LICENSE_NOT_FOUND));

        if (playRecord.getScore() >= LicensePolicy.STANDARD_ACQUISITION_SCORE) {
            if (playRecord.getKind().equals("bartender")) {
                license.updateBartender();
            }
            if (playRecord.getKind().equals("baker")) {
                license.updateBaker();
            }
        }

        return RecordSaveResponse.of(playRecord);
    }

    @Transactional(readOnly = true)
    public FeedbackReadResponse getFeedBack(FeedbackReadRequest request) {
        PlayRecord playRecord = playRecordRepository.findTopByNameOrderByIdDesc(request.getName())
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_EXISTS_USER_RECORD));

        return FeedbackReadResponse.of(playRecord);
    }
}

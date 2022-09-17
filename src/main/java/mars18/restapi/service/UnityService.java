package mars18.restapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars18.restapi.dto.UnityDto;
import mars18.restapi.dto.WebLicenseDto;
import mars18.restapi.entity.License;
import mars18.restapi.entity.PlayRecord;
import mars18.restapi.exception.CustomException;
import mars18.restapi.repository.UnityRepository;
import mars18.restapi.repository.WebLicenseRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.constant.Constable;
import java.util.Optional;

import static mars18.restapi.exception.CustomErrorCode.NULL_USER_NAME;
import static mars18.restapi.exception.CustomErrorCode.REGISTER_INFO_NULL;
import static mars18.restapi.model.StatusTrue.RECORD_STATUS_TRUE;

@Service
@RequiredArgsConstructor
@Slf4j
public class UnityService {

    private final UnityRepository unityRepository;
    private final WebLicenseRepository webLicenseRepository;

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

        if(!(webLicenseRepository.existsByName(request.getName()))) {
            webLicenseRepository.save(
                    License.builder()
                            .name(request.getName())
                            .bartender(Boolean.FALSE)
                            .baker(Boolean.FALSE)
                            .build()
            );
        } // db 필드에 받은 이름을 가진 레코드가 없으면 생성

        if(webLicenseRepository.existsByName(request.getName())) {
            Optional<License> oLicense = Optional.ofNullable(webLicenseRepository.findByName(request.getName())); // name에 해당하는 레코드 객체
            if(oLicense.isPresent()) { // oLicense 객체가 존재하는지
                License license = oLicense.get();
                license.setName(request.getName());
                license.setBartender(getBartender(request));
                license.setBaker(getBaker(request));
                webLicenseRepository.save(license);
            }
        } // db 필드에 받은 이름을 가진 레코드 수정

        return RECORD_STATUS_TRUE;
    }

    public Boolean getBartender(UnityDto.Request request) {

        return unityRepository.existsByNameAndKindAndScoreGreaterThanEqual(request.getName(), "bartender", 60);
    }

    public Boolean getBaker(UnityDto.Request request) {

        return unityRepository.existsByNameAndKindAndScoreGreaterThanEqual(request.getName(), "baker", 60);
    }

    //예외처리

    private void PLAYRECORD_VALIDATION(UnityDto.Request request) {
        if (request.getName() == null)
            throw new CustomException(NULL_USER_NAME); // 이름 비었을 때
    }
}

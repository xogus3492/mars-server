package mars18.restapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars18.restapi.dto.UnityDto;
import mars18.restapi.domain.license.domain.License;
import mars18.restapi.domain.playrecord.domain.PlayRecord;
import mars18.restapi.global.exception.CustomException;
import mars18.restapi.domain.playrecord.domain.repository.PlayRecordRepository;
import mars18.restapi.domain.license.domain.repository.LicenseRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static mars18.restapi.global.exception.CustomErrorCode.NULL_USER_NAME;

@Service
@RequiredArgsConstructor
@Slf4j
public class UnityService {

    private final PlayRecordRepository playRecordRepository;
    private final LicenseRepository licenseRepository;

    @Transactional
    public Void playRecord(UnityDto.Request request) {
        PLAYRECORD_VALIDATION(request);

        playRecordRepository.save(
                PlayRecord.builder()
                        .name(request.getName())
                        .score(request.getScore())
                        //.playingTime(request.getPlayingTime())
                        //.kind(request.getKind())
                        .build()
        );

        if (!(licenseRepository.existsByName(request.getName()))) {
            licenseRepository.save(
                    License.builder()
                            .name(request.getName())
                            .bartender(Boolean.FALSE)
                            .baker(Boolean.FALSE)
                            .build()
            );
        } // db 필드에 받은 이름을 가진 레코드가 없으면 생성

//        if(licenseRepository.existsByName(request.getName())) {
//            Optional<License> oLicense = Optional.ofNullable(licenseRepository.findByName(request.getName())); // name에 해당하는 레코드 객체
//            if(oLicense.isPresent()) { // oLicense 객체가 존재하는지
//                License license = oLicense.get();
//                license.setName(request.getName());
//                license.setBartender(getBartender(request));
//                license.setBaker(getBaker(request));
//                licenseRepository.save(license);
//            }
//        } // db 필드에 받은 이름을 가진 레코드 수정

        return null;
    }

    public Boolean getBartender(UnityDto.Request request) {

        return playRecordRepository.existsByNameAndKindAndScoreGreaterThanEqual(request.getName(), "bartender", 60);
    }

    public Boolean getBaker(UnityDto.Request request) {

        return playRecordRepository.existsByNameAndKindAndScoreGreaterThanEqual(request.getName(), "baker", 60);
    }

    //예외처리

    private void PLAYRECORD_VALIDATION(UnityDto.Request request) {
        if (request.getName() == null)
            throw new CustomException(NULL_USER_NAME); // 이름 비었을 때
    }
}

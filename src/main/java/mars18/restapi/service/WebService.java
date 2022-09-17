package mars18.restapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars18.restapi.dto.AppFeedbackDto;
import mars18.restapi.dto.WebLicenseDto;
import mars18.restapi.entity.License;
import mars18.restapi.entity.PlayRecord;
import mars18.restapi.exception.CustomException;
import mars18.restapi.repository.UnityRepository;
import mars18.restapi.repository.WebLicenseRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static mars18.restapi.exception.CustomErrorCode.NOT_EXISTS_USER_RECORD;
import static mars18.restapi.exception.CustomErrorCode.NULL_USER_NAME;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebService {

    private final UnityRepository unityRepository;
    private final WebLicenseRepository webLicenseRepository;

    @Transactional
    public Map<Object, Object> getLicenseAcquiredYN(WebLicenseDto.Request request) {
        //WEBLICENSE_VALIDATION(request);

        if(!(webLicenseRepository.existsByName(request.getName()))) {
            webLicenseRepository.save(
                    License.builder()
                            .name(request.getName())
                            .bartender(Boolean.FALSE)
                            .baker(Boolean.TRUE)
                            .build()
            );
        }

        if(webLicenseRepository.existsByName(request.getName())) {
            Optional<License> oLicense = Optional.ofNullable(webLicenseRepository.findByName(request.getName())); // name에 해당하는 레코드 객체
            if(oLicense.isPresent()) { // oLicense 객체가 존재하는지
                License license = oLicense.get();
                license.setName(request.getName());
                license.setBartender(getBartender(request));
                license.setBaker(getBaker(request));
                webLicenseRepository.save(license);
            }
        }

        Map<Object, Object> license = new HashMap<>();
        license.put("bartender", webLicenseRepository.findByName(request.getName()).getBartender());
        license.put("baker", webLicenseRepository.findByName(request.getName()).getBaker());

        return license;
    }

    public Boolean getBartender(WebLicenseDto.Request request) {

        return unityRepository.existsByNameAndKindAndScoreGreaterThanEqual(request.getName(), "bartender", 60);
    }

    public Boolean getBaker(WebLicenseDto.Request request) {

        return unityRepository.existsByNameAndKindAndScoreGreaterThanEqual(request.getName(), "baker", 60);
    }
    // 예외 처리

    private void WEBLICENSE_VALIDATION(AppFeedbackDto.Request request) {}
}

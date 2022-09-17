package mars18.restapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars18.restapi.dto.AppFeedbackDto;
import mars18.restapi.dto.WebLicenseDto;
import mars18.restapi.repository.UnityRepository;
import mars18.restapi.repository.WebLicenseRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebService {

    private final UnityRepository unityRepository;
    private final WebLicenseRepository webLicenseRepository;

    @Transactional
    public Map<Object, Object> getLicenseAcquiredYN(WebLicenseDto.Request request) {
        //WEBLICENSE_VALIDATION(request);

        Map<Object, Object> license = new HashMap<>();
        license.put("bartender", webLicenseRepository.findByName(request.getName()).getBartender());
        license.put("baker", webLicenseRepository.findByName(request.getName()).getBaker());

        return license;
    }

    // 예외 처리

    private void WEBLICENSE_VALIDATION(AppFeedbackDto.Request request) {}
}

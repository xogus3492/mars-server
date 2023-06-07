package mars18.restapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars18.restapi.dto.AppFeedbackDto;
import mars18.restapi.dto.WebLicenseDto;
import mars18.restapi.domain.playrecord.domain.repository.PlayRecordRepository;
import mars18.restapi.domain.license.domain.repository.LicenseRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebService {

    private final PlayRecordRepository playRecordRepository;
    private final LicenseRepository licenseRepository;

    @Transactional
    public Map<Object, Object> getLicenseAcquiredYN(WebLicenseDto.Request request) {
        //WEBLICENSE_VALIDATION(request);

        Map<Object, Object> license = new HashMap<>();
        license.put("bartender", licenseRepository.findByName(request.getName()).getBartender());
        license.put("baker", licenseRepository.findByName(request.getName()).getBaker());

        return license;
    }

    // 예외 처리

    private void WEBLICENSE_VALIDATION(AppFeedbackDto.Request request) {}
}

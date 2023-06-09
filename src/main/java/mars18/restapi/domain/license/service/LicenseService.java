package mars18.restapi.domain.license.service;

import lombok.RequiredArgsConstructor;
import mars18.restapi.domain.license.domain.License;
import mars18.restapi.domain.license.domain.repository.LicenseRepository;
import mars18.restapi.domain.license.dto.IsAcquiredLicenseResponse;
import mars18.restapi.domain.user.domain.User;
import mars18.restapi.domain.user.domain.repository.UserRepository;
import mars18.restapi.global.common.dto.CommonRequest;
import mars18.restapi.global.exception.CustomErrorCode;
import mars18.restapi.global.exception.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LicenseService {

    private final UserRepository userRepository;
    private final LicenseRepository licenseRepository;

    public IsAcquiredLicenseResponse isAcquiredLicense(CommonRequest request) {
        User user = userRepository.findByName(request.getName())
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        License license = licenseRepository.findByName(user.getName())
                .orElseThrow(() -> new CustomException(CustomErrorCode.LICENSE_NOT_FOUND));

        return IsAcquiredLicenseResponse.of(license);
    }
}

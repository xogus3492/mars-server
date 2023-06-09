package mars18.restapi.domain.license.controller;

import lombok.RequiredArgsConstructor;
import mars18.restapi.domain.license.dto.IsAcquiredLicenseResponse;
import mars18.restapi.domain.license.service.LicenseService;
import mars18.restapi.global.common.dto.CommonRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LicenseController {

    private final LicenseService licenseService;

    @PostMapping("/license")
    public ResponseEntity<IsAcquiredLicenseResponse> isAcquiredLicense(
            @RequestBody @Valid final CommonRequest request
    ) {
        IsAcquiredLicenseResponse response = licenseService.isAcquiredLicense(request);

        return ResponseEntity.ok(response);
    }
}

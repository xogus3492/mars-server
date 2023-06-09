package mars18.restapi.domain.license.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import mars18.restapi.domain.license.domain.License;

@Getter
@Builder
@AllArgsConstructor
public class IsAcquiredLicenseResponse {
    private Boolean bartender;
    private Boolean baker;

    public static IsAcquiredLicenseResponse of(License license) {
        return IsAcquiredLicenseResponse.builder()
                .bartender(license.getBartender())
                .baker(license.getBaker())
                .build();
    }
}

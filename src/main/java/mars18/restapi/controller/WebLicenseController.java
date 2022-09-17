package mars18.restapi.controller;

import lombok.RequiredArgsConstructor;
import mars18.restapi.dto.UserLoginDto;
import mars18.restapi.dto.WebLicenseDto;
import mars18.restapi.service.WebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("web")
public class WebLicenseController {

    private final WebService webService;

    @PostMapping("license")
    public Map<Object, Object> licenseAcquiredYN(@Valid @RequestBody final WebLicenseDto.Request request) {
        return webService.getLicenseAcquiredYN(request);
    }
}

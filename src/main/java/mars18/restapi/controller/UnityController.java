package mars18.restapi.controller;

import lombok.RequiredArgsConstructor;
import mars18.restapi.dto.UnityDto;
import mars18.restapi.dto.UserRegisterDto;
import mars18.restapi.service.UnityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UnityController {

    private final UnityService unityService;

    @PostMapping("play")
    public Void playRecord(@Valid @RequestBody final UnityDto.Request request) {
        return unityService.playRecord(request);
    }
}

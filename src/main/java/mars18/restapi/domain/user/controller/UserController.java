package mars18.restapi.domain.user.controller;

import lombok.RequiredArgsConstructor;
import mars18.restapi.domain.user.dto.*;
import mars18.restapi.domain.user.service.UserService;
import mars18.restapi.global.common.dto.CommonRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CommonUserResponse> Signup(
            @RequestBody @Valid final SignUpRequest request
    ) {
        CommonUserResponse response = userService.Signup(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<CommonUserResponse> login(
            @RequestBody @Valid final LoginRequest request
    ) {
        CommonUserResponse response = userService.login(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/my/record")
    public ResponseEntity<List<MyRecordResponse>> getMyRecord(
            @RequestBody @Valid final CommonRequest request
    ) {
        List<MyRecordResponse> responses = userService.getMyRecord(request);

        return ResponseEntity.ok(responses);
    }

    @PostMapping("/my/update")
    public ResponseEntity<UpdateMyNameResponse> updateName(
            @RequestBody @Valid final UpdateMyNameRequest request
    ) {
        UpdateMyNameResponse response = userService.updateName(request);

        return ResponseEntity.ok(response);
    }
}
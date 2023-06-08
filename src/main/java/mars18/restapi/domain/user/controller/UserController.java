package mars18.restapi.domain.user.controller;

import lombok.RequiredArgsConstructor;
import mars18.restapi.domain.user.dto.CommonUserResponse;
import mars18.restapi.domain.user.dto.SignUpRequest;
import mars18.restapi.dto.UserLoginDto;
import mars18.restapi.domain.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
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
    public Map<Object, Object> loginUser(@Valid @RequestBody final UserLoginDto.Request request) {
        return userService.loginUser(request);
    }
}
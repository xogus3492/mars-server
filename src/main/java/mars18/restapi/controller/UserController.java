package mars18.restapi.controller;

import lombok.RequiredArgsConstructor;
import mars18.restapi.dto.UserLoginDto;
import mars18.restapi.dto.UserRegisterDto;
import mars18.restapi.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.lang.constant.Constable;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("register")
    public Constable registerUser(@Valid @RequestBody final UserRegisterDto.Request request) {
        return userService.registerUser(request);
    }

    @PostMapping("login")
    public Map<Object, Object> loginUser(@Valid @RequestBody final UserLoginDto.Request request) {
        return userService.loginUser(request);
    }
}
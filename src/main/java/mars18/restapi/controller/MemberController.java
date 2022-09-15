package mars18.restapi.controller;

import lombok.RequiredArgsConstructor;
import mars18.restapi.dto.MemberLoginDto;
import mars18.restapi.dto.MemberRegisterDto;
import mars18.restapi.service.MemberService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.lang.constant.Constable;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("register")
    public Constable registerMember(@Valid @RequestBody final MemberRegisterDto.Request request) {
        return memberService.registerMember(request);
    }
}
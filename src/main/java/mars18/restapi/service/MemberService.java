package mars18.restapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars18.restapi.dto.MemberLoginDto;
import mars18.restapi.dto.MemberRegisterDto;
import mars18.restapi.entity.Member;
import mars18.restapi.exception.CustomException;
import mars18.restapi.repository.MemberRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.constant.Constable;

import static mars18.restapi.exception.CustomErrorCode.*;
import static mars18.restapi.model.StatusTrue.REGISTER_STATUS_TRUE;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    //private final PasswordEncoder passwordEncoder;

    @Transactional
    public Constable registerMember(MemberRegisterDto.Request request) {
        REGISTER_VALIDATION(request);

        memberRepository.save(
                Member.builder()
                        .name(request.getName())
                        .email(request.getEmail())
                        .pw(request.getPw())
                        .build()
        );

         return REGISTER_STATUS_TRUE;
    }

    // validate 단순화

    private void REGISTER_VALIDATION(MemberRegisterDto.Request request) {
        if (request.getEmail() == null || request.getPw() == null || request.getName() == null)
            throw new CustomException(REGISTER_INFO_NULL); // 필수 항목

        if (memberRepository.existsByEmail(request.getEmail()))
            throw new CustomException(DUPLICATE_USER_EMAIL); // 이메일중복

        if (!request.getEmail().contains("@"))
            throw new CustomException(NOT_EMAIL_FORM); // 이메일 형식

        if (!(request.getPw().length() > 5))
            throw new CustomException(PASSWORD_SIZE_ERROR); // 비밀번호 6자리 이상

        if (!(request.getPw().contains("!") || request.getPw().contains("@") || request.getPw().contains("#")
                || request.getPw().contains("$") || request.getPw().contains("%") || request.getPw().contains("^")
                || request.getPw().contains("&") || request.getPw().contains("*") || request.getPw().contains("(")
                || request.getPw().contains(")"))
        ) {
            throw new CustomException(NOT_CONTAINS_EXCLAMATIONMARK); // 특수 기호 포함
        }

        if (memberRepository.existsByName(request.getName()))
            throw new CustomException(DUPLICATE_USER_NAME);
        }
}

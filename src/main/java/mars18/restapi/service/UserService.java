package mars18.restapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars18.restapi.dto.UserLoginDto;
import mars18.restapi.dto.UserRegisterDto;
import mars18.restapi.domain.user.domain.User;
import mars18.restapi.global.exception.CustomException;
import mars18.restapi.domain.user.domain.repository.UserRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.constant.Constable;
import java.util.HashMap;
import java.util.Map;

import static mars18.restapi.global.exception.CustomErrorCode.*;
import static mars18.restapi.global.model.StatusTrue.REGISTER_STATUS_TRUE;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    //private final PasswordEncoder passwordEncoder;

    @Transactional
    public Constable registerUser(UserRegisterDto.Request request) {
        REGISTER_VALIDATION(request);

        userRepository.save(
                User.builder()
                        .name(request.getName())
                        .email(request.getEmail())
                        .pw(request.getPw())
                        .build()
        );

         return REGISTER_STATUS_TRUE;
    }

    @Transactional
    public Map<Object, Object> loginUser(UserLoginDto.Request request) {
        LOGIN_VALIDATION(request);

        Map<Object, Object> name = new HashMap<>();
        name.put("name", userRepository.findNameByEmail(request.getEmail()).getName());

        return name;
    }

    // validate 처리

    // 회원가입 예외
    private void REGISTER_VALIDATION(UserRegisterDto.Request request) {
        String name = request.getName();

        if (request.getEmail() == null || request.getPw() == null || request.getName() == null)
            throw new CustomException(REGISTER_INFO_NULL); // 필수 항목

        if (userRepository.existsByEmail(request.getEmail()))
            throw new CustomException(DUPLICATE_USER_EMAIL); // 이메일중복

        if (!request.getEmail().contains("@"))
            throw new CustomException(NOT_EMAIL_FORM); // 이메일 형식

        if (!(request.getPw().length() > 5))
            throw new CustomException(PASSWORD_SIZE_ERROR); // 비밀번호 6자리 이상

        if (!(request.getPw().contains("!") || request.getPw().contains("@") || request.getPw().contains("#")
                || request.getPw().contains("$") || request.getPw().contains("%") || request.getPw().contains("^")
                || request.getPw().contains("&") || request.getPw().contains("*") || request.getPw().contains("(")
                || request.getPw().contains(")")))
            throw new CustomException(NOT_CONTAINS_EXCLAMATIONMARK); // 특수 기호 포함


        if (!(request.getName().length() > 1 && request.getName().length() < 9))
            throw new CustomException(LIMIT_NAME_LENGTH); // 1 < 이름 길이 < 9

        if (name.contains("!") || name.contains("@") || name.contains("#") || name.contains("$")
                || name.contains("%") || name.contains("^") || name.contains("&")  || name.contains(")")
                || name.contains("*") || name.contains("(") )
            throw new CustomException(NO_CONTAINS_IN_NAME); // 특수 기호 포함 X

        if (userRepository.existsByName(request.getName()))
            throw new CustomException(DUPLICATE_USER_NAME); // 이름 중복
        }

        //로그인 예외
        private void LOGIN_VALIDATION(UserLoginDto.Request request) {
            if (request.getEmail() == null)
                throw new CustomException(LOGIN_EMAIL_NULL); // 이메일 입력X

            if (request.getPw() == null)
                throw new CustomException(LOGIN_PW_NULL); // 패스워드 입력X

            if (!(request.getEmail().contains("@")))
                throw new CustomException(NOT_EMAIL_FORM); // 이메일 형식

            if (!(userRepository.existsByEmail(request.getEmail())) || !(userRepository.existsByPw(request.getPw())))
                throw new CustomException(NOT_EMAIL_OR_PW); // 아이디 또는 패스 또는 둘다 일치X
        }

}

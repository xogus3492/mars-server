package mars18.restapi.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars18.restapi.domain.user.dto.CommonUserResponse;
import mars18.restapi.domain.user.dto.SignUpRequest;
import mars18.restapi.dto.UserLoginDto;
import mars18.restapi.dto.UserRegisterDto;
import mars18.restapi.domain.user.domain.User;
import mars18.restapi.global.exception.CustomException;
import mars18.restapi.domain.user.domain.repository.UserRepository;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

import static mars18.restapi.global.exception.CustomErrorCode.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CommonUserResponse Signup(SignUpRequest request) {
        SignupValidation(request);

        User user = userRepository.save(request.toEntity(passwordEncoder.encode(request.getPassword())));

        return CommonUserResponse.of(user);
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
    private void SignupValidation(SignUpRequest request) {
        String specialCharacters = ".*[!@#$%^&*()].*";

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new CustomException(DUPLICATE_USER_EMAIL);
        }

        if (!request.getPassword().matches(specialCharacters)) {
            throw new CustomException(NOT_CONTAINS_EXCLAMATIONMARK);
        }

        if (request.getName().matches(specialCharacters)) {
            throw new CustomException(NOT_CONTAINS_IN_NAME);
        }

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

            if (!(userRepository.existsByEmail(request.getEmail())) || !(userRepository.existsByPassword(request.getPw())))
                throw new CustomException(NOT_EMAIL_OR_PW); // 아이디 또는 패스 또는 둘다 일치X
        }
}

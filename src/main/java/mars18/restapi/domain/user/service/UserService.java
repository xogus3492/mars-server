package mars18.restapi.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars18.restapi.domain.user.dto.CommonUserResponse;
import mars18.restapi.domain.user.dto.LoginRequest;
import mars18.restapi.domain.user.dto.SignUpRequest;
import mars18.restapi.dto.UserLoginDto;
import mars18.restapi.dto.UserRegisterDto;
import mars18.restapi.domain.user.domain.User;
import mars18.restapi.global.exception.CustomErrorCode;
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
        signupValidation(request);

        User user = userRepository.save(request.toEntity(passwordEncoder.encode(request.getPassword())));

        return CommonUserResponse.of(user);
    }

    @Transactional
    public CommonUserResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        loginValidation(user, request);

        return CommonUserResponse.of(user);
    }

    // exception
    private void signupValidation(SignUpRequest request) {
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
            throw new CustomException(DUPLICATE_USER_NAME);
    }

    private void loginValidation(User user, LoginRequest request) {
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new CustomException(NOT_MATCH_PW);
    }
}

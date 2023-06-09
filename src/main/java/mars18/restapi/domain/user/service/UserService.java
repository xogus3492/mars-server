package mars18.restapi.domain.user.service;

import lombok.RequiredArgsConstructor;
import mars18.restapi.domain.license.domain.repository.LicenseRepository;
import mars18.restapi.domain.playrecord.domain.repository.PlayRecordRepository;
import mars18.restapi.domain.user.dto.*;
import mars18.restapi.domain.user.domain.User;
import mars18.restapi.global.common.dto.CommonRequest;
import mars18.restapi.global.error.CustomException;
import mars18.restapi.domain.user.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

import static mars18.restapi.global.error.CustomErrorCode.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PlayRecordRepository playRecordRepository;
    private final LicenseRepository licenseRepository;
    private final PasswordEncoder passwordEncoder;

    public CommonUserResponse Signup(SignUpRequest request) {
        signupValidation(request);

        User user = userRepository.save(request.toEntity(passwordEncoder.encode(request.getPassword())));

        return CommonUserResponse.of(user);
    }

    @Transactional(readOnly = true)
    public CommonUserResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        loginValidation(user, request);

        return CommonUserResponse.of(user);
    }

    @Transactional(readOnly = true)
    public List<MyRecordResponse> getMyRecord(CommonRequest request) {
        User user = userRepository.findByName(request.getName())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        List<MyRecordResponse> responses = playRecordRepository.findAllByNameOrderByIdDesc(request.getName())
                .stream()
                .map(p -> MyRecordResponse.of(p))
                .collect(Collectors.toList());

        return responses;
    }

    public UpdateMyNameResponse updateName(UpdateMyNameRequest request) {
        User user = userRepository.findByName(request.getBeforeName())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        nameDuplicate(request.getAfterName());

        playRecordRepository.findAllByName(user.getName())
                .stream()
                .forEach(p -> p.updateName(request.getAfterName()));

        if(licenseRepository.existsByName(user.getName())) {
            licenseRepository.findByName(user.getName())
                    .orElseThrow(() -> new CustomException(LICENSE_NOT_FOUND))
                    .updateName(request.getAfterName());
        }

        user.updateName(request.getAfterName());

        return UpdateMyNameResponse.of(user);
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
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException(NOT_MATCH_PW);
        }
    }

    private void nameDuplicate(String afterName) {
        if (userRepository.existsByName(afterName)) {
            throw new CustomException(DUPLICATE_USER_NAME);
        }
    }
}

package mars18.restapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mars18.restapi.dto.*;
import mars18.restapi.domain.license.domain.License;
import mars18.restapi.domain.playrecord.domain.PlayRecord;
import mars18.restapi.domain.user.domain.User;
import mars18.restapi.global.exception.CustomException;
import mars18.restapi.domain.playrecord.domain.repository.PlayRecordRepository;
import mars18.restapi.domain.user.domain.repository.UserRepository;
import mars18.restapi.domain.license.domain.repository.LicenseRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static mars18.restapi.global.exception.CustomErrorCode.*;
import static mars18.restapi.global.model.StatusTrue.COMPLETE_UPDATE_INFO;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppService {

    private final PlayRecordRepository playRecordRepository;
    private final LicenseRepository licenseRepository;
    private final UserRepository userRepository;

    @Transactional
    public AppFeedbackDto.Response feedbackTapData(AppFeedbackDto.Request request) {
        FEEDBACKTAP_VALIDATION(request);

        return AppFeedbackDto.Response.response(
                PlayRecord.builder()
                        //.name(playRecordRepository.findTopByNameOrderByIdDesc(request.getName()).getName())
                        //.kind(playRecordRepository.findTopByNameOrderByIdDesc(request.getName()).getKind())
                        //.score(playRecordRepository.findTopByNameOrderByIdDesc(request.getName()).getScore())
                        .build()
        );
    }

//    @Transactional
//    public List<Object> myPageTapData(AppMyDto.Request request) {
//        //MYPAGETAP_VALIDATION(request);
//
//        List<Object> parentList = new ArrayList<>();
//        List<Map<Object, Object>> childList = new ArrayList<>();
//        Map<Object, Object> noArrMap = new HashMap<>();
//
//        noArrMap.put("name", request.getName());
//        noArrMap.put("bartender", licenseRepository.findByName(request.getName()).getBartender());
//        noArrMap.put("baker", licenseRepository.findByName(request.getName()).getBaker());
//
//        // kind, ranking, score, play_at, playing_time
//
//        List<PlayRecord> recordData = playRecordRepository.findByNameOrderByScoreDesc(request.getName());
//        // 점수 내림차순 정렬한 name과 같은 이름의 레코드(들)
//
//        System.out.println("recordData.size() = " + recordData.size());
//        for(int i = 0; i < recordData.size(); i++) {
//            Map<Object, Object> arrMap = new HashMap<>();
//            arrMap.put("kind", recordData.get(i).getKind());
//            arrMap.put("ranking", i + 1);
//            arrMap.put("score", recordData.get(i).getScore());
//            arrMap.put("play_at", recordData.get(i).getCreateAt());
//            arrMap.put("playing_time", recordData.get(i).getPlaying_time());
//
//            childList.add(arrMap);
//        }
//
//        parentList.add(noArrMap);
//        parentList.add(childList);
//
//        return parentList;
//    }

    @Transactional
    public Map<Object, Object> updateGetInfo(AppMyUpdateDto.Request request) {
        //GET_INFO_VALIDATION(request);

        Map<Object, Object> info = new HashMap<>();
        info.put("name", userRepository.findByName(request.getName()).getName());
        info.put("pw", userRepository.findByName(request.getName()).getPw());

        return info;
    }

//    @Transactional
//    public Constable updateInfo(AppMyUpdateDto.Request request) {
//        INFO_UPDATE_VALIDATION(request);
//
//        Optional<User> oUser = Optional.ofNullable(userRepository.findByName(request.getName())); // name에 해당하는 레코드 객체
//        if(oUser.isPresent()) { // oUser 객체가 존재하는지
//            User user = oUser.get();
//            user.setName(request.getUpdateName());
//            user.setPw(request.getUpdatePw());
//            userRepository.save(user);
//        }// db user table 필드에 받은 이름을 가진 레코드 수정
//
//        Optional<License> oLicense = Optional.ofNullable(licenseRepository.findByName(request.getName())); // name에 해당하는 레코드 객체
//        if(oLicense.isPresent()) { // oUser 객체가 존재하는지
//            License license = oLicense.get();
//            license.setName(request.getUpdateName());
//            licenseRepository.save(license);
//        }// db license table 필드에 받은 이름을 가진 레코드 수정
//
//        List<PlayRecord> lPlayRecord = playRecordRepository.findByName(request.getName()); // name에 해당하는 레코드 객체
//            for (int i = 0; i < lPlayRecord.size(); i++) {
//                PlayRecord playRecord = lPlayRecord.get(i);
//                playRecord.setName(request.getUpdateName());
//                playRecordRepository.save(playRecord);
//            }
//        // db play_record table 필드에 받은 이름을 가진 레코드 수정
//
//        return COMPLETE_UPDATE_INFO;
//    }

    // 예외 처리

    private void FEEDBACKTAP_VALIDATION(AppFeedbackDto.Request request) {
        if (request.getName() == null)
        throw new CustomException(NULL_USER_NAME); // 이름 비었을 때 (처리 안되는 이유?)
    }

    private void MYPAGETAP_VALIDATION(AppMyDto.Request request) {}

    private void INFO_UPDATE_VALIDATION(AppMyUpdateDto.Request request) {
        String name = request.getUpdateName();

        if (request.getUpdateName() == null)
            throw new CustomException(NULL_USER_UPDATE_NAME); // 이름 비었을 때

        if (request.getUpdatePw() == null)
            throw new CustomException(NULL_USER_UPDATE_PW); // 패스워드 비었을 때

        if (!(request.getUpdateName().length() > 1 && request.getUpdateName().length() < 9))
            throw new CustomException(LIMIT_NAME_LENGTH); // 1 < 이름 길이 < 9

        if (userRepository.existsByName(request.getUpdateName()))
            throw new CustomException(DUPLICATE_USER_NAME); // 이름 중복

        if (name.contains("!") || name.contains("@") || name.contains("#") || name.contains("$")
                || name.contains("%") || name.contains("^") || name.contains("&")  || name.contains(")")
                || name.contains("*") || name.contains("(") || name.contains("0") )
            throw new CustomException(NO_CONTAINS_IN_NAME); // 특수 기호 포함 X

        if (!(request.getUpdatePw().length() > 5))
            throw new CustomException(PASSWORD_SIZE_ERROR); // 비밀번호 6자리 이상

        if (!(request.getUpdatePw().contains("!") || request.getUpdatePw().contains("@") || request.getUpdatePw().contains("#")
                || request.getUpdatePw().contains("$") || request.getUpdatePw().contains("%") || request.getUpdatePw().contains("^")
                || request.getUpdatePw().contains("&") || request.getUpdatePw().contains("*") || request.getUpdatePw().contains("(")
                || request.getUpdatePw().contains(")")))
            throw new CustomException(NOT_CONTAINS_EXCLAMATIONMARK); // 특수 기호 포함

    }
}

package mars18.restapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor // enum에 하나의 프로퍼티가 있는데 프로퍼티를 사용해서 enum을 하나 생성해줄때 메세지를 넣어서 생성해주는 생성자가 없어서 선언해줘야함
@Getter
public enum CustomErrorCode {
    //회원가입
    REGISTER_INFO_NULL("필수 항목을 입력하지 않았습니다."),
    PASSWORD_SIZE_ERROR("비밀번호가 6자리 이상이여야 합니다."),
    NOT_EMAIL_FORM("이메일 형식이 아닙니다."),
    NOT_CONTAINS_EXCLAMATIONMARK("비밀번호에 특수문자가 포함되어있지 않습니다."),
    DUPLICATE_USER_EMAIL("존재하는 이메일입니다."),
    DUPLICATE_USER_NAME("존재하는 이름입니다.");


    private final String statusMessage;
}

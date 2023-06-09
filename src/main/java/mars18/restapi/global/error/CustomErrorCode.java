package mars18.restapi.global.error;

public enum CustomErrorCode {

    // signup
    NOT_CONTAINS_EXCLAMATIONMARK("비밀번호에 특수문자가 포함되어있지 않습니다."),
    DUPLICATE_USER_EMAIL("존재하는 이메일입니다."),
    DUPLICATE_USER_NAME("존재하는 이름입니다."),
    NOT_CONTAINS_IN_NAME("이름에 기호를 입력할 수 없습니다."),

    // login
    NOT_MATCH_PW("패스워드가 일치하지 않습니다."),

    // entity not found
    LICENSE_NOT_FOUND("자격증 정보를 찾을 수 없습니다."),
    PLAY_RECORD_NOT_FOUND("유저의 플레이 기록이 존재하지 않습니다."),
    USER_NOT_FOUND("유저를 찾을 수 없습니다.");

    private final String statusMessage;

    CustomErrorCode(final String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}

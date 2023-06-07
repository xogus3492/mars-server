package mars18.restapi.global.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusTrue {
    REGISTER_STATUS_TRUE("REGISTER_STATUS_TRUE", "회원가입 성공"),
    RECORD_STATUS_TRUE("RECORD_STATUS_TRUE", "플레이 기록 저장 완료"),
    COMPLETE_UPDATE_INFO("COMPLETE_UPDATE_INFO", "정보 수정 완료");

    private final String status;
    private final String statusMessage;
}

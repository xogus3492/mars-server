package mars18.restapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusTrue {
    REGISTER_STATUS_TRUE("REGISTER_STATUS_TRUE", "회원가입 성공");

    private final String status;
    private final String statusMessage;
}

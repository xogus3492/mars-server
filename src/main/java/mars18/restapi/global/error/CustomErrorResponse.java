package mars18.restapi.global.error;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomErrorResponse {
    private CustomErrorCode status;
    private String statusMessage;

    @Builder
    public CustomErrorResponse(CustomErrorCode status, String statusMessage) {
        this.status = status;
        this.statusMessage = statusMessage;
    }
}

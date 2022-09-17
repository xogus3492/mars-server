package mars18.restapi.dto;

import lombok.*;
import org.springframework.lang.Nullable;

public class AppMyUpdateDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{

        private String name;
        private String updateName;
        private String updatePw;
    }
}

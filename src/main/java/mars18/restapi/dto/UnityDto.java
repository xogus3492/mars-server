package mars18.restapi.dto;

import com.sun.istack.NotNull;
import lombok.*;

public class UnityDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        private String name;
        private int score;
        private String playing_time;
        private String kind;
    }
}

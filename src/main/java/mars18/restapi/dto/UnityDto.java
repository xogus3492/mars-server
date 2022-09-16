package mars18.restapi.dto;

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

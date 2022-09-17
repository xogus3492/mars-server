package mars18.restapi.dto;

import lombok.*;
public class AppMyDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        private String name;
    }
}

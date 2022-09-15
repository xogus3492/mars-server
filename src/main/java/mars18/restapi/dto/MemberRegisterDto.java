package mars18.restapi.dto;

import lombok.*;

public class MemberRegisterDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        private String name;
        private String email;
        private String pw;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response{
        private String name;
        private String email;
    }
}

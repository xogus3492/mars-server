package mars18.restapi.dto;

import com.sun.istack.NotNull;
import lombok.*;
import mars18.restapi.entity.Member;

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

        public static Response entityResponse(@NotNull Member member){
            return Response.builder()
                    .name(member.getName())
                    .email(member.getEmail())
                    .build();
        }
    }
}

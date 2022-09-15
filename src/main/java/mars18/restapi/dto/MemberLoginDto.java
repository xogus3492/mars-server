package mars18.restapi.dto;

import com.sun.istack.NotNull;
import lombok.*;
import mars18.restapi.entity.Member;

public class MemberLoginDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        @NotNull
        private String email;
        @NotNull
        private String pw;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response{
        private String pw;
        private String email;

        public static MemberRegisterDto.Response entityResponse(@NotNull Member member){
            return MemberRegisterDto.Response.builder()
                    .name(member.getPw())
                    .email(member.getEmail())
                    .build();
        }
    }
}

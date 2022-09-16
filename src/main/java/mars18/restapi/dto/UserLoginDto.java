package mars18.restapi.dto;

import com.sun.istack.NotNull;
import lombok.*;

public class UserLoginDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        private String email;
        private String pw;
    }

    /*@Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response{
        private String name;

        public static Response entityResponse(Member member){
            return Response.builder()
                    .name(member.getName())
                    .build();
        }
    }*/
    //Resonse.entityRsponse를 사용하려면 매개변수로 member형을 줘야하는데 그렇게되면
    //UserRepository의 save메소드를 이용해야하기 때문에 db에 불필요한 데이터가 쌓여 사용하지 않음
}

package mars18.restapi.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import mars18.restapi.domain.user.domain.User;

@Getter
@Builder
@AllArgsConstructor
public class CommonUserResponse {

    private Long id;
    private String name;

    public static CommonUserResponse of(User user) {
        return CommonUserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}

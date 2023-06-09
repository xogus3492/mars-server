package mars18.restapi.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import mars18.restapi.domain.user.domain.User;

@Getter
@Builder
@AllArgsConstructor
public class UpdateMyNameResponse {
    private String updateName;

    public static UpdateMyNameResponse of(User user) {
        return UpdateMyNameResponse.builder()
                .updateName(user.getName())
                .build();
    }
}

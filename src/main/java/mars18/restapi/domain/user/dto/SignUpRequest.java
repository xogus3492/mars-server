package mars18.restapi.domain.user.dto;

import lombok.Getter;
import mars18.restapi.domain.user.domain.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class SignUpRequest {

    @Email
    @NotBlank
    @Size(max = 50)
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    @NotBlank
    @Size(max = 5)
    private String name;

    public User toEntity(String pw) {
        return User.builder()
                .email(email)
                .password(pw)
                .name(name)
                .build();
    }
}

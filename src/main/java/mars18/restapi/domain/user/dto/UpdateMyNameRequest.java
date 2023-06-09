package mars18.restapi.domain.user.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class UpdateMyNameRequest {
    @NotBlank
    private String beforeName;

    @NotBlank
    @Size(max = 5)
    private String afterName;
}

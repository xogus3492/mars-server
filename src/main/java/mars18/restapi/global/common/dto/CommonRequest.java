package mars18.restapi.global.common.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CommonRequest {
    @NotBlank
    private String name;
}

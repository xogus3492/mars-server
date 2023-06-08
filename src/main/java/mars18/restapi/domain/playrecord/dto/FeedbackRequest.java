package mars18.restapi.domain.playrecord.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class FeedbackRequest {

    @NotBlank
    private String name;
}

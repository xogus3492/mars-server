package mars18.restapi.domain.playrecord.dto;

import lombok.Getter;
import mars18.restapi.domain.playrecord.domain.PlayRecord;
import mars18.restapi.domain.playrecord.domain.PlayRole;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class RecordSaveRequest {
    @NotBlank
    private String name;
    @NotNull
    private int score;
    @NotBlank
    private String playingTime;
    @NotNull
    private PlayRole kind;

    public PlayRecord toEntity() {
        return PlayRecord.builder()
                .name(name)
                .score(score)
                .playingTime(playingTime)
                .kind(kind)
                .build();
    }
}

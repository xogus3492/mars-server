package mars18.restapi.domain.playrecord.dto;

import lombok.Getter;
import mars18.restapi.domain.playrecord.domain.PlayRecord;

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
    @NotBlank
    private String kind;

    public PlayRecord toEntity() {
        return PlayRecord.builder()
                .name(name)
                .score(score)
                .playingTime(playingTime)
                .kind(kind)
                .build();
    }
}

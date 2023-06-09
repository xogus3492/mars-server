package mars18.restapi.domain.playrecord.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import mars18.restapi.domain.playrecord.domain.PlayRecord;
import mars18.restapi.domain.playrecord.domain.PlayRole;

@Getter
@Builder
@AllArgsConstructor
public class RecordSaveResponse {

    private Long id;
    private String name;
    private int score;
    private String playing_time;
    private PlayRole kind;

    public static RecordSaveResponse of(PlayRecord playRecord) {
        return RecordSaveResponse.builder()
                .id(playRecord.getId())
                .name(playRecord.getName())
                .score(playRecord.getScore())
                .playing_time(playRecord.getPlayingTime())
                .kind(playRecord.getKind())
                .build();
    }
}

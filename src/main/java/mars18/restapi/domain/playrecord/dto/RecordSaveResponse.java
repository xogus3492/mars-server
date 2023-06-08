package mars18.restapi.domain.playrecord.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import mars18.restapi.domain.playrecord.domain.PlayRecord;

@Getter
@Builder
@AllArgsConstructor
public class RecordSaveResponse {

    private Long id;
    private String name;
    private int score;
    private String playing_time;
    private String kind;

    public static RecordSaveResponse of(PlayRecord playRecord) {
        return RecordSaveResponse.builder()
                .id(playRecord.getId())
                .name(playRecord.getName())
                .score(playRecord.getScore())
                .playing_time(playRecord.getPlaying_time())
                .kind(playRecord.getKind())
                .build();
    }
}

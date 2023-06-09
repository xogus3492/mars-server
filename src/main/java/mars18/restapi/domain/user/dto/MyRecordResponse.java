package mars18.restapi.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import mars18.restapi.domain.playrecord.domain.PlayRecord;
import mars18.restapi.domain.playrecord.domain.PlayRole;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class MyRecordResponse {
    private Long id;
    private String name;
    private PlayRole kind;
    private Integer score;
    private String playingTime;
    private LocalDateTime playAt;

    public static MyRecordResponse of(PlayRecord playRecord) {
        return MyRecordResponse.builder()
                .id(playRecord.getId())
                .name(playRecord.getName())
                .kind(playRecord.getKind())
                .score(playRecord.getScore())
                .playingTime(playRecord.getPlayingTime())
                .playAt(playRecord.getCreatedTime())
                .build();
    }
}

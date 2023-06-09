package mars18.restapi.domain.playrecord.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import mars18.restapi.domain.playrecord.domain.PlayRecord;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class RankingResponse {

    private Long ranking;
    private String name;
    private String kind;
    private Integer score;
    private String playingTime;
    private LocalDateTime playAt;

    public static RankingResponse of(Long ranking, PlayRecord playRecord) {
        return RankingResponse.builder()
                .ranking(ranking)
                .name(playRecord.getName())
                .kind(playRecord.getKind())
                .score(playRecord.getScore())
                .playingTime(builder().playingTime)
                .playAt(playRecord.getCreatedTime())
                .build();
    }
}

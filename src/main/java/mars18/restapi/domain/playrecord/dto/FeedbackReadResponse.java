package mars18.restapi.domain.playrecord.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import mars18.restapi.domain.playrecord.domain.PlayRecord;

@Getter
@Builder
@AllArgsConstructor
public class FeedbackReadResponse {
    private String name;
    private String kind;
    private int score;

    public static FeedbackReadResponse of(PlayRecord playRecord) {
        return FeedbackReadResponse.builder()
                .name(playRecord.getName())
                .kind(playRecord.getKind())
                .score(playRecord.getScore())
                .build();
    }
}

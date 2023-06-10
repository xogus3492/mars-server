package mars18.restapi.domain.playrecord.domain;

import lombok.*;
import mars18.restapi.global.common.BaseTimeEntity;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayRecord extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "play_record_id")
    private Long id;

    @Column(length = 5, nullable = false)
    private String name;

    @Column(nullable = false)
    private int score;

    @Column(nullable = false)
    private String playingTime;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlayRole kind;

    @Builder
    public PlayRecord(Long id, String name, int score, String playingTime, PlayRole kind) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.playingTime = playingTime;
        this.kind = kind;
    }

    public void updateName(String afterName) {
        this.name = afterName;
    }
}

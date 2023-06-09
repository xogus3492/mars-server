package mars18.restapi.domain.playrecord.domain;

import lombok.*;
import mars18.restapi.global.common.BaseTimeEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayRecord extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int score;
    private String playingTime;
    @Enumerated(EnumType.STRING)
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

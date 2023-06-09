package mars18.restapi.domain.license.domain;

import lombok.*;
import mars18.restapi.domain.playrecord.domain.PlayRecord;
import mars18.restapi.global.common.BaseTimeEntity;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class License extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean bartender;
    private Boolean baker;

    @Builder
    public License(Long id, String name, Boolean bartender, Boolean baker) {
        this.id = id;
        this.name = name;
        this.bartender = bartender;
        this.baker = baker;
    }

    public static License toEntity(PlayRecord playRecord) {
        return License.builder()
                .name(playRecord.getName())
                .bartender(false)
                .baker(false)
                .build();
    }

    public void acquireBartender() {
        this.bartender = true;
    }

    public void acquireBaker() {
        this.baker = true;
    }

    public void updateName(String afterName) {
        this.name = afterName;
    }
}

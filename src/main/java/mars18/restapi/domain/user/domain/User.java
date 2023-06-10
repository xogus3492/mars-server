package mars18.restapi.domain.user.domain;

import lombok.*;
import mars18.restapi.global.common.BaseTimeEntity;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 5, unique = true, nullable = false)
    private String name;

    @Builder
    public User(Long id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public void updateName(String afterName) {
        this.name = afterName;
    }
}

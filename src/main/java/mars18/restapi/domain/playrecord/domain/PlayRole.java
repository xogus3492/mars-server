package mars18.restapi.domain.playrecord.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PlayRole {
    BARTENDER, BAKER;

    @JsonCreator
    public static PlayRole fromString(String value) {
        return PlayRole.valueOf(value.toUpperCase());
    }
}

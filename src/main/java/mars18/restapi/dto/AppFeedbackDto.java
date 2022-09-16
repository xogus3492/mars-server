package mars18.restapi.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import mars18.restapi.entity.PlayRecord;

import javax.validation.constraints.NotNull;

public class AppFeedbackDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        private String name;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class Response{
        private String name;
        private String kind;
        private int score;

        public static AppFeedbackDto.Response response(@NotNull PlayRecord playRecord){
            return Response.builder()
                    .name(playRecord.getName())
                    .kind(playRecord.getKind())
                    .score(playRecord.getScore())
                    .build();
        }
    }
}

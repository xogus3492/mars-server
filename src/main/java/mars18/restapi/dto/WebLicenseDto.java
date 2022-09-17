package mars18.restapi.dto;

import lombok.*;

public class WebLicenseDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        private String name;
    }
}

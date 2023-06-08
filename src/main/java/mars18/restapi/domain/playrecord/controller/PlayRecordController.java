package mars18.restapi.domain.playrecord.controller;

import lombok.RequiredArgsConstructor;
import mars18.restapi.domain.playrecord.dto.FeedbackRequest;
import mars18.restapi.domain.playrecord.dto.FeedbackResponse;
import mars18.restapi.domain.playrecord.service.PlayRecordService;
import mars18.restapi.dto.AppFeedbackDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PlayRecordController {

    private final PlayRecordService playRecordService;

    @PostMapping("/feedback")
    public ResponseEntity<FeedbackResponse> getFeedback(
            @RequestBody @Valid final FeedbackRequest request
    ) {
        FeedbackResponse response = playRecordService.getFeedBack(request);

        return ResponseEntity.ok().body(response);
    }
}

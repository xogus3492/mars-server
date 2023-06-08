package mars18.restapi.domain.playrecord.controller;

import lombok.RequiredArgsConstructor;
import mars18.restapi.domain.playrecord.dto.FeedbackReadRequest;
import mars18.restapi.domain.playrecord.dto.FeedbackReadResponse;
import mars18.restapi.domain.playrecord.dto.RecordSaveRequest;
import mars18.restapi.domain.playrecord.dto.RecordSaveResponse;
import mars18.restapi.domain.playrecord.service.PlayRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PlayRecordController {

    private final PlayRecordService playRecordService;

    @PostMapping("/play")
    public ResponseEntity<RecordSaveResponse> saveRecord(
            @RequestBody @Valid final RecordSaveRequest request)
    {
        RecordSaveResponse response = playRecordService.saveRecord(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/feedback")
    public ResponseEntity<FeedbackReadResponse> getFeedback(
            @RequestBody @Valid final FeedbackReadRequest request
    ) {
        FeedbackReadResponse response = playRecordService.getFeedBack(request);

        return ResponseEntity.ok(response);
    }
}

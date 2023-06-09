package mars18.restapi.domain.playrecord.controller;

import lombok.RequiredArgsConstructor;
import mars18.restapi.domain.playrecord.dto.*;
import mars18.restapi.domain.playrecord.service.PlayRecordService;
import mars18.restapi.dto.WebLicenseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/ranking")
    public ResponseEntity<List<RankingResponse>> getRanking() {
        List<RankingResponse> responses = playRecordService.getRanking();

        return ResponseEntity.ok(responses);
    }

    @PostMapping("/feedback")
    public ResponseEntity<FeedbackReadResponse> getFeedback(
            @RequestBody @Valid final FeedbackReadRequest request
    ) {
        FeedbackReadResponse response = playRecordService.getFeedBack(request);

        return ResponseEntity.ok(response);
    }
}

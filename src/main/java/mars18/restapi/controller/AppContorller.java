package mars18.restapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import mars18.restapi.dto.AppFeedbackDto;
import mars18.restapi.dto.AppMyDto;
import mars18.restapi.service.AppService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.ListIterator;

@RestController
@RequiredArgsConstructor
@RequestMapping("app")
public class AppContorller {

    private final AppService appService;

    @ResponseBody
    @PostMapping("feedback")
    public AppFeedbackDto.Response feedbackTapData(@Valid @RequestBody final AppFeedbackDto.Request request) {

        return appService.feedbackTapData(request);
    }

    @ResponseBody
    @PostMapping("my")
    public List<Object> myPageTapData(@Valid @RequestBody final AppMyDto.Request request) {

        return appService.myPageTapData(request);
    }
}

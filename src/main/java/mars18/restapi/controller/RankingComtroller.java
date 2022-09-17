package mars18.restapi.controller;

import lombok.RequiredArgsConstructor;
import mars18.restapi.service.RankingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RankingComtroller {

    private final RankingService rankingService;

    @GetMapping("ranking")
    public List<Map<Object, Object>> RankingData() {
        return rankingService.rankingData();
    }
}

package com.canopyv5.CANOPYV5.scores;

import com.canopyv5.CANOPYV5.rescues.Rescue;
import com.canopyv5.CANOPYV5.rescues.RescueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/scores")
public class ScoreController {

    private final ScoreService scoreService;

    @Autowired
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping
    public List<String> getScores() throws SQLException {
        return scoreService.getScores();
    }
}

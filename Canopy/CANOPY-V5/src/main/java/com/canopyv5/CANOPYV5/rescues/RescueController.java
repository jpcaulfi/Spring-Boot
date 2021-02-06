package com.canopyv5.CANOPYV5.rescues;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/rescues")
public class RescueController {

    private final RescueService rescueService;

    @Autowired
    public RescueController(RescueService rescueService) {
        this.rescueService = rescueService;
    }

    @GetMapping
    public List<Rescue> getRescues() throws SQLException {
        return rescueService.getRescues();
    }
}

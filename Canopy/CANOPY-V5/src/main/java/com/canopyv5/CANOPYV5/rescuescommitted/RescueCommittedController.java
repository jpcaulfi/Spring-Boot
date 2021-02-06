package com.canopyv5.CANOPYV5.rescuescommitted;

import com.canopyv5.CANOPYV5.rescues.Rescue;
import com.canopyv5.CANOPYV5.rescues.RescueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/rescuescommitted")
public class RescueCommittedController {

    private final RescueCommittedService rescueCommittedService;

    @Autowired
    public RescueCommittedController(RescueCommittedService rescueCommittedService) {
        this.rescueCommittedService = rescueCommittedService;
    }

    @GetMapping
    public List<RescueCommitted> getRescuesCommitted() {
        return rescueCommittedService.getRescuesCommitted();
    }
}

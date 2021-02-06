package com.canopyv5.CANOPYV5.rescuesupforbid;

import com.canopyv5.CANOPYV5.rescues.Rescue;
import com.canopyv5.CANOPYV5.rescues.RescueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/rescuesupforbid")
public class RescueUpForBidController {

    private final RescueUpForBidService rescueUpForBidService;

    @Autowired
    public RescueUpForBidController(RescueUpForBidService rescueUpForBidService) {
        this.rescueUpForBidService = rescueUpForBidService;
    }

    @GetMapping
    public List<RescueUpForBid> getRescuesUpForBid() {
        return rescueUpForBidService.getRescuesUpForBid();
    }
}
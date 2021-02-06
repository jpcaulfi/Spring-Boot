package com.canopyv5.CANOPYV5.rescuesupforbid;

import com.canopyv5.CANOPYV5.rescuescommitted.RescueCommitted;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class RescueUpForBidService {

    public List<RescueUpForBid> getRescuesUpForBid() {
        return List.of(
                new RescueUpForBid(
                        297,
                        LocalDate.of(2000,3,15),
                        LocalTime.of(15,5,0),
                        "15228-198",
                        "Johnathan",
                        20
                ),
                new RescueUpForBid(
                        298,
                        LocalDate.of(2000,3,15),
                        LocalTime.of(15,15,0),
                        "15228-198",
                        "Patty",
                        50
                )
        );
    }
}

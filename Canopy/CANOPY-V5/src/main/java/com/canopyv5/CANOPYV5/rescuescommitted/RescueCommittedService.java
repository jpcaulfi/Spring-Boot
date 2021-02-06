package com.canopyv5.CANOPYV5.rescuescommitted;

import com.canopyv5.CANOPYV5.rescues.Rescue;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class RescueCommittedService {

    public List<RescueCommitted> getRescuesCommitted() {
        return List.of(
                new RescueCommitted(
                        294,
                        LocalDate.of(2000,3,15),
                        LocalTime.of(15,5,0),
                        "15228-198",
                        "Brendan",
                        "Jacob",
                        20
                ),
                new RescueCommitted(
                        295,
                        LocalDate.of(2000,3,15),
                        LocalTime.of(15,15,0),
                        "15228-198",
                        "Shaq",
                        "Michelle",
                        20
                ),
                new RescueCommitted(
                        296,
                        LocalDate.of(2000,3,15),
                        LocalTime.of(15,25,0),
                        "15228-198",
                        "Rock",
                        "Becky",
                        40
                )
        );
    }
}

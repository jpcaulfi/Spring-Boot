package com.canopyv5.CANOPYV5.rescues;

import com.canopyv5.CANOPYV5.components.Database;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RescueService {

    public List<Rescue> getRescues() throws SQLException {

        List<Rescue> rescuesList = new ArrayList<>();
        String pullRescuesStatement = "SELECT * FROM rescues ORDER BY rescueid";
        ResultSet pullRescuesResult = Database.pullFromDB(pullRescuesStatement);
        while (true) {
            assert pullRescuesResult != null;
            if (!pullRescuesResult.next()) break;
            rescuesList.add(new Rescue(Integer.parseInt(pullRescuesResult.getString("rescueid")),
                    LocalDate.of(Integer.parseInt(pullRescuesResult.getString("date").split("-")[0]),
                            Integer.parseInt(pullRescuesResult.getString("date").split("-")[1]),
                            Integer.parseInt(pullRescuesResult.getString("date").split("-")[2])),
                    LocalTime.of(Integer.parseInt(pullRescuesResult.getString("time").split(":")[0]),
                            Integer.parseInt(pullRescuesResult.getString("time").split(":")[1])
                    ), pullRescuesResult.getString("routeid"),
                    pullRescuesResult.getString("saviorid"),
                    pullRescuesResult.getString("subjectid"),
                    Integer.parseInt(pullRescuesResult.getString("stops").split("\\.")[0])));
        }
        return rescuesList;
    }

}

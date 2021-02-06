package com.canopyv5.CANOPYV5.scores;

import com.canopyv5.CANOPYV5.components.Database;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreService {

    public List<String> getScores() throws SQLException {

        List<String> scoresList = new ArrayList<>();
        String pullScoresStatement = "SELECT * FROM scores ORDER BY driverid";
        ResultSet pullScoresResult = Database.pullFromDB(pullScoresStatement);
        while (true) {
            assert pullScoresResult != null;
            if (!pullScoresResult.next()) break;
            scoresList.add(new Score(
                    pullScoresResult.getString("driverid"),
                    pullScoresResult.getString("driverinitials"),
                    pullScoresResult.getString("drivername"),
                    pullScoresResult.getDouble("stopspeed"),
                    pullScoresResult.getDouble("packagespeed"),
                    pullScoresResult.getDouble("stopspeedscore"),
                    pullScoresResult.getDouble("packagespeedscore"),
                    pullScoresResult.getDouble("rescuesfor"),
                    pullScoresResult.getDouble("rescuesagainst"),
                    pullScoresResult.getDouble("rescueratio"),
                    pullScoresResult.getDouble("rescueratioscore"),
                    pullScoresResult.getDouble("attendancescore"),
                    pullScoresResult.getDouble("markscore"),
                    pullScoresResult.getDouble("canopyscore")
            ).toString());
        }
        return scoresList;
    }
}

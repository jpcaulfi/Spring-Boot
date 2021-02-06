import components.*;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static components.Database.clearTable;

public class Query {

    public static void main(String fileName, List<List<String>> DSPAttendance, List<List<String>> DSPMarks, List<List<String>> DSPRoster, List<List<String>> RouteLocations) throws Exception {

        marksQuery(fileName, DSPMarks);
        attendanceQuery(fileName, DSPAttendance);

        String refDate = "2021-0" + fileName.split("_")[0] + "-" + fileName.split("_")[1];
        String time = "NULL";
        int[] hoursArray = new int[] {10, 11, 12, 13, 14 ,15, 16, 17, 18, 19, 20, 21, 22, 23, 24};
        int[] minutesArray = new int[] {0, 3};
        for (int hour : hoursArray) {
            for (int minute : minutesArray) {
                List<RawData> dataList = new ArrayList<>();
                List<Route> routesList = new ArrayList<>();
                List<Run> runsList = new ArrayList<>();
                String bigPullStatement = "SELECT * FROM rawdata WHERE date = '" + refDate + "' AND hour = '" + hour + "' AND minute LIKE '" + minute + "%'";
                ResultSet bigResult = Database.pullFromDB(bigPullStatement);
                while (true) {
                    assert bigResult != null;
                    if (!bigResult.next()) break;
                    RawData thisRawData = new RawData(bigResult.getString("urlkey"), bigResult.getString("url"),
                            bigResult.getString("date"), bigResult.getString("time"), bigResult.getString("hour"),
                            bigResult.getString("minute"), bigResult.getInt("countoftotalstopsr"), bigResult.getInt("countofcompletedstopsr"),
                            bigResult.getString("routeid"), bigResult.getString("routecode"), bigResult.getString("companyid"),
                            bigResult.getInt("counttotal"), bigResult.getInt("countdelivered"), bigResult.getInt("countattempted"),
                            bigResult.getInt("countreturning"), bigResult.getInt("countdeliveredandattempted"), bigResult.getDouble("currentcompletion"),
                            bigResult.getString("transporterid"), bigResult.getString("transportername"), bigResult.getString("id"),
                            bigResult.getString("name"), bigResult.getInt("numberofpackages"), bigResult.getString("planneddeparturetime"),
                            bigResult.getString("servicetypename"), bigResult.getString("driverid"), bigResult.getInt("countofattemptedpackages"),
                            bigResult.getInt("countofcompletedpackages"), bigResult.getInt("countofcompletedstops"), bigResult.getInt("countofdeliveredpackages"),
                            bigResult.getInt("countofmissingpackages"), bigResult.getInt("countofreattemptablepackages"), bigResult.getInt("countofremainingpackages"), bigResult.getInt("countofreturnedpackages"),
                            bigResult.getInt("countofreturningpackages"), bigResult.getInt("countoftotalpackages"), bigResult.getInt("countoftotalstops"),
                            bigResult.getInt("countofundeliverablepackages"), bigResult.getInt("packagesahead"), bigResult.getInt("packagesbehind"),
                            bigResult.getString("riskmodelused"), bigResult.getString("riskstatus"), bigResult.getInt("stopsahead"),
                            bigResult.getInt("stopsbehind"), bigResult.getString("kittriskstatus"), bigResult.getString("starttime"),
                            bigResult.getString("departuretime"), bigResult.getString("calculationtime"), bigResult.getDouble("initialhour"),
                            bigResult.getDouble("packagerate"), bigResult.getDouble("delta"), bigResult.getInt("remainingstops"),
                            bigResult.getDouble("targetcompletion"), bigResult.getInt("timeleft"), bigResult.getString("departureendtime"),
                            bigResult.getDouble("stopsrate"));
                    dataList.add(thisRawData);
                    time = bigResult.getString("time");
                }

                for (RawData rawDataElement : dataList) {


                    //////////////  UPDATING ROUTE DATA (ROUTES TABLE)  //////////////

                    String routeid = rawDataElement.getRouteId();
                    String routecode = rawDataElement.getRouteCode();
                    String routelocation = "Unknown Geo-Location";

                    // REFERENCING ROUTES TO LOCATIONS USING ROUTE LOCATIONS CSV FILE

                    for (List<String> RouteLocationItem : RouteLocations) {
                        if (RouteLocationItem.get(0).equals(routeid)) {
                            routelocation = RouteLocationItem.get(1);
                        }
                    }
                    Route thisRoute = new Route(routeid, routecode, routelocation);
                    thisRoute.insert();
                    routesList.add(thisRoute);


                    //driverQuery(rawDataElement);

                    //////////////  UPDATING DRIVER DATA (DRIVERS TABLE)  //////////////

                    String driverid = rawDataElement.getDriverId();
                    String initials = rawDataElement.getName();
                    String name = "Name Unknown";

                    // REFERENCING INITIALS TO NAMES USING DSP ROSTER CSV FILE

                    for (List<String> DSPRosterItem : DSPRoster) {
                        if (DSPRosterItem.get(0).equals(driverid)) {
                            name = DSPRosterItem.get(2);
                        }
                    }
                    Driver thisDriver = new Driver(driverid, initials, name);
                    thisDriver.insert();


                    //////////////  UPDATING RUNS DATA (RUNS TABLE) -- 1  //////////////

                    Run thisRun = new Run(rawDataElement.getRouteId(), rawDataElement.getDriverId(), rawDataElement.getCountOfTotalPackages(), rawDataElement.getCountOfTotalStops(), rawDataElement.getDate(), "SAVIOR");
                    runsList.add(thisRun);


                    //////////////  LOGGING METRICS DATA (METRICS TABLE)  //////////////

                    if (hour != 10 && (hour != 11 || minute != 0) && (hour != 21) && (hour != 22) && (hour != 23) && (hour != 24)) {
                        Metric thisMetric = new Metric(rawDataElement.getDriverId(), rawDataElement.getRouteId(), rawDataElement.getDate(), rawDataElement.getTime(), rawDataElement.getCountOfCompletedStops(), rawDataElement.getCountOfCompletedPackages());
                        thisMetric.insert();
                    }


                }

                for (Route thisRoute : routesList) {
                    List<Run> runCopies = new ArrayList<>();
                    for (Run thisRun : runsList) {
                        if (thisRun.getRouteId().equals(thisRoute.getRouteId())) {
                            runCopies.add(thisRun);
                        }
                    }
                    int lastStops = 0;
                    int runCopiesCounter = 0;
                    int primeRun = 0;
                    for (Run runCopy : runCopies) {
                        int currentStops = runCopy.getStops();
                        if (currentStops > lastStops) {
                            primeRun = runCopiesCounter;
                        }
                        lastStops = currentStops;
                        runCopiesCounter += 1;
                    }
                    runCopies.get(primeRun).setOwner("OWNER");

                    String runDriverId = "NULL";
                    List<Run> runDBList = new ArrayList<>();
                    String pullRunDBStatement = "SELECT driverid, stops, packages, owner FROM runs WHERE routeid = '" + thisRoute.getRouteId() + "' AND date = '" + refDate + "' GROUP BY driverid";
                    ResultSet runDBResult = Database.pullFromDB(pullRunDBStatement);
                    while (true) {
                        assert runDBResult != null;
                        if (!runDBResult.next()) break;
                        if (runDBResult.getString("owner").equals("OWNER")) {
                            runDriverId = runDBResult.getString("driverid");
                        }
                        Run pulledRun = new Run(thisRoute.getRouteId(), runDBResult.getString("driverid"), Integer.parseInt(runDBResult.getString("packages")), Integer.parseInt(runDBResult.getString("stops")), refDate, runDBResult.getString("owner"));
                        runDBList.add(pulledRun);
                    }
                    for (Run runCopy : runCopies) {
                        int matchFound = 0;
                        //if (!runCopy.getOwner().equals("OWNER")) {
                        for (Run runDB : runDBList) {
                            // If it does appear in the list, we want to take the difference and key on that run
                            if (runCopy.getDriverId().equals(runDB.getDriverId())) {
                                //
                                if (runCopy.getStops() - runDB.getStops() > 9 && runCopy.getStops() - runDB.getStops() < 80) {
                                    Rescue thisRescue = new Rescue(refDate, time, thisRoute.getRouteId(),
                                            runCopy.getDriverId(), runDriverId,
                                            runCopy.getStops() - runDB.getStops(),
                                            runCopy.getPackages() - runDB.getPackages());
                                    thisRescue.insert();
                                }
                                matchFound = 1;
                                // If it doesn't appear in the list, we want to log a fresh rescue
                            }
                        }
                        if (matchFound == 0) {
                            if (runCopy.getStops() > 9 && runCopy.getStops() < 80) {
                                Rescue thisRescue = new Rescue(refDate, time, thisRoute.getRouteId(),
                                        runCopy.getDriverId(), runDriverId,
                                        runCopy.getStops(), runCopy.getPackages());
                                thisRescue.insert();
                            }
                        }
                    }

                    String wipeRunsStatement = "DELETE FROM runs WHERE routeid = '" + thisRoute.getRouteId() + "' AND date = '" + refDate + "'";
                    Database.commitToDB(wipeRunsStatement);
                    for (Run runCopy : runCopies) {
                        Run updatedRun = new Run(runCopy.getRouteId(), runCopy.getDriverId(), runCopy.getPackages(), runCopy.getStops(), runCopy.getDate(), runCopy.getOwner());
                        updatedRun.insert();
                    }
                }



            }
        }


        routeMetricsQueryA();
        routeMetricsQueryB();
        driverMetricsQueryA();
        driverMetricsQueryB();
        dvrMetricsQuery();
        rescueRatiosQueryA();
        rescueRatiosQueryB();
        coreScoreQuery();

    }



    public static void marksQuery(String fileName, List<List<String>> DSPMarks) throws SQLException {

        //////////////  LOG MANUAL MARKS DATA (MARKS TABLE)  //////////////

        String marksDriverName;
        String marksDriverId;
        String markType = "UNDEFINED";
        double markScore = 0;
        String markDate = fileName.split("_")[0] + "/" + fileName.split("_")[1] + "/2021";
        for (List<String> marksItem : DSPMarks) {
            if (marksItem.get(1).equals(markDate)) {
                marksDriverName = marksItem.get(0);
                while (Character.toString(marksDriverName.charAt(marksDriverName.length() - 1)).equals(" ")) {
                    marksDriverName = removeLastChar(marksDriverName);
                }
                if (marksItem.get(2).equalsIgnoreCase("STAR")) {
                    markType = "STAR";
                    markScore = 20;
                } else if (marksItem.get(2).equalsIgnoreCase("DEMERIT")) {
                    markType = "DEMERIT";
                    markScore = -20;
                }
                marksDriverId = matchDriverNameToId(marksDriverName);
                Mark thisMark = new Mark(marksDriverId, markDate, markType, markScore);
                thisMark.insert();
            }
        }
    }

    public static void attendanceQuery(String fileName, List<List<String>> DSPAttendance) throws SQLException {


        //////////////  LOG ATTENDANCE DATA (ATTENDANCE TABLE)  //////////////

        String attendanceDriverName;
        String attendanceDriverId;
        String attendanceType = "type not determined";
        double attendanceScore = 0;
        String attendanceDate = fileName.split("_")[0] + "/" + fileName.split("_")[1] + "/2021";
        for (List<String> attendanceItem : DSPAttendance) {
            if (attendanceItem.get(1).equals(attendanceDate)) {
                attendanceDriverName = attendanceItem.get(0);
                while (Character.toString(attendanceDriverName.charAt(attendanceDriverName.length() - 1)).equals(" ")) {
                    attendanceDriverName = removeLastChar(attendanceDriverName);
                }
                if (attendanceItem.get(2).equals("TRUE")) {
                    attendanceType = "SICK";
                    attendanceScore = -5;
                } else if (attendanceItem.get(3).equals("TRUE")) {
                    attendanceType = "NCNS";
                    attendanceScore = -20;
                } else if (attendanceItem.get(4).equals("TRUE")) {
                    attendanceType = "OTHER";
                    attendanceScore = -10;
                } else if (attendanceItem.get(5).equals("TRUE")) {
                    attendanceType = "LATE";
                    attendanceScore = -10;
                } else if (attendanceItem.get(6).equals("TRUE")) {
                    attendanceType = "LEFT EARLY";
                    attendanceScore = -5;
                }
                attendanceDriverId = matchDriverNameToId(attendanceDriverName);
                Attendance thisAttendance = new Attendance(attendanceDriverId, attendanceDate, attendanceType, attendanceScore);
                thisAttendance.insert();
            }
        }
    }





    public static void routeMetricsQueryA() throws SQLException {
        clearTable("routemetricsa");
        String pullStatement = "SELECT routeid, driverid, date, time, stopsdone, packagesdone FROM metrics ORDER BY routeid ASC, driverid ASC, date ASC, time ASC";
        ResultSet result = Database.pullFromDB(pullStatement);
        while (true) {
            assert result != null;
            if (!result.next()) break;
            String pushStatement = "INSERT INTO routemetricsa (routeid, driverid, date, time, stopsdone, packagesdone) VALUES ('"+result.getString("routeid")+"', '"+result.getString("driverid")+"', '"+result.getString("date")+"', '"+result.getString("time")+"', '"+result.getString("stopsdone")+"', '"+result.getString("packagesdone")+"')";
            Database.commitToDB(pushStatement);
        }
    }

    public static void routeMetricsQueryB() throws SQLException {
        clearTable("routemetricsb");
        String pullStatement = "SELECT routeid, driverid, date, time, stopsdone, packagesdone FROM routemetricsa";
        ResultSet result = Database.pullFromDB(pullStatement);
        int lastStopsDone = 0;
        int thisStopsDone = 0;
        double stopSpeed = 0;
        int lastPackagesDone = 0;
        int thisPackagesDone = 0;
        double packageSpeed = 0;
        while (true) {
            assert result != null;
            if (!result.next()) break;
            if (result.getString("time").split(":")[0].equals("11")) {
                lastStopsDone = Integer.parseInt(result.getString("stopsdone"));
                lastPackagesDone = Integer.parseInt(result.getString("packagesdone"));
            } else {
                thisStopsDone = Integer.parseInt(result.getString("stopsdone"));
                stopSpeed = Math.abs(thisStopsDone - lastStopsDone);
                thisPackagesDone = Integer.parseInt(result.getString("packagesdone"));
                packageSpeed = Math.abs(thisPackagesDone - lastPackagesDone);
                if (stopSpeed != 0) {
                    String pushStatement = "INSERT INTO routemetricsb (routeid, driverid, date, time, stopspeed, packagespeed) VALUES ('" + result.getString("routeid") + "', '" + result.getString("driverid") + "', '" + result.getString("date") + "', '" + result.getString("time") + "', '" + stopSpeed + "', '" + packageSpeed + "')";
                    Database.commitToDB(pushStatement);
                }
                lastStopsDone = Integer.parseInt(result.getString("stopsdone"));
                lastPackagesDone = Integer.parseInt(result.getString("packagesdone"));
            }
        }
    }

    public static void driverMetricsQueryA() throws SQLException {
        clearTable("drivermetricsa");
        String pullStatement = "SELECT driverid, routeid, date, time, stopsdone, packagesdone FROM metrics ORDER BY driverid ASC, routeid ASC, date ASC, time ASC";
        ResultSet result = Database.pullFromDB(pullStatement);
        while (true) {
            assert result != null;
            if (!result.next()) break;
            String pushStatement = "INSERT INTO drivermetricsa (driverid, routeid, date, time, stopsdone, packagesdone) VALUES ('"+result.getString("driverid")+"', '"+result.getString("routeid")+"', '"+result.getString("date")+"', '"+result.getString("time")+"', '"+result.getString("stopsdone")+"', '"+result.getString("packagesdone")+"')";
            Database.commitToDB(pushStatement);
        }
    }

    public static void driverMetricsQueryB() throws SQLException {
        clearTable("drivermetricsb");
        String pullStatement = "SELECT driverid, routeid, date, time, stopsdone, packagesdone FROM drivermetricsa";
        ResultSet result = Database.pullFromDB(pullStatement);
        int lastStopsDone = 0;
        int thisStopsDone = 0;
        double stopSpeed = 0;
        int lastPackagesDone = 0;
        int thisPackagesDone = 0;
        double packageSpeed = 0;
        while (true) {
            assert result != null;
            if (!result.next()) break;
            if (result.getString("time").split(":")[0].equals("11")) {
                lastStopsDone = Integer.parseInt(result.getString("stopsdone"));
                lastPackagesDone = Integer.parseInt(result.getString("packagesdone"));
            } else {
                thisStopsDone = Integer.parseInt(result.getString("stopsdone"));
                stopSpeed = Math.abs(thisStopsDone - lastStopsDone);
                thisPackagesDone = Integer.parseInt(result.getString("packagesdone"));
                packageSpeed = Math.abs(thisPackagesDone - lastPackagesDone);
                if (stopSpeed != 0) {
                    String pushStatement = "INSERT INTO drivermetricsb (driverid, routeid, date, time, stopspeed, packagespeed) VALUES ('" + result.getString("driverid") + "', '" + result.getString("routeid") + "', '" + result.getString("date") + "', '" + result.getString("time") + "', '" + stopSpeed + "', '" + packageSpeed + "')";
                    Database.commitToDB(pushStatement);
                }
                lastStopsDone = Integer.parseInt(result.getString("stopsdone"));
                lastPackagesDone = Integer.parseInt(result.getString("packagesdone"));
            }
        }
    }

    public static double[] dspMetricsQuery() throws SQLException {
        double[] dspMetricsResults = new double[2];
        String pullStatement = "SELECT stopspeed, packagespeed FROM drivermetricsb";
        ResultSet result = Database.pullFromDB(pullStatement);
        int denominator = 0;
        double numeratorS = 0;
        double numeratorP = 0;
        while (true) {
            assert result != null;
            if (!result.next()) break;
            numeratorS += Double.parseDouble(result.getString("stopspeed"));
            numeratorP += Double.parseDouble(result.getString("packagespeed"));
            denominator += 1;
        }
        dspMetricsResults[0] = numeratorS/denominator;
        dspMetricsResults[1] = numeratorP/denominator;
        return dspMetricsResults;
    }

    public static void dvrMetricsQuery() throws SQLException {
        clearTable("dvrmetrics");
        double denominatorS = dspMetricsQuery()[0];
        double denominatorP = dspMetricsQuery()[1];
        String pullDriversStatement = "SELECT * FROM drivers";
        ResultSet driversResult = Database.pullFromDB(pullDriversStatement);
        while (true) {
            assert driversResult != null;
            if (!driversResult.next()) break;
            String pullStatement = "SELECT driverid, AVG(stopspeed), AVG(packagespeed) FROM drivermetricsb WHERE driverid = '" + driversResult.getString("driverid") + "' GROUP BY driverid";
            ResultSet result = Database.pullFromDB(pullStatement);
            while (true) {
                assert result != null;
                if (!result.next()) {
                    String pushStatement = "INSERT INTO dvrmetrics (driverid, stopspeed, packagespeed, stopspeedscore, packagespeedscore) VALUES ('" + driversResult.getString("driverid") + "', '0', '0', '100', '100')";
                    Database.commitToDB(pushStatement);
                    break;
                }
                double stopspeedscore = (1 + (Double.parseDouble(result.getString("AVG(stopspeed)")) - denominatorS)/denominatorS) * 100;
                double packagespeedscore = (1 + (Double.parseDouble(result.getString("AVG(packagespeed)")) - denominatorP)/denominatorP) * 100;
                String pushStatement = "INSERT INTO dvrmetrics (driverid, stopspeed, packagespeed, stopspeedscore, packagespeedscore) VALUES ('" + result.getString("driverid") + "', '" + result.getString("AVG(stopspeed)") + "', '" + result.getString("AVG(packagespeed)") + "', '" + stopspeedscore + "', '" + packagespeedscore + "')";
                Database.commitToDB(pushStatement);
            }
        }
    }

    public static void dvrMetricsQuery_Archived() throws SQLException {
        double denominatorS = dspMetricsQuery()[0];
        double denominatorP = dspMetricsQuery()[1];
        String pullStatement = "SELECT driverid, AVG(stopspeed), AVG(packagespeed) FROM drivermetricsb GROUP BY driverid";
        ResultSet result = Database.pullFromDB(pullStatement);
        while (true) {
            assert result != null;
            if (!result.next()) break;
            double stopspeedscore = (1 + (Double.parseDouble(result.getString("AVG(stopspeed)")) - denominatorS)/denominatorS) * 100;
            double packagespeedscore = (1 + (Double.parseDouble(result.getString("AVG(packagespeed)")) - denominatorP)/denominatorP) * 100;
            String pushStatement = "INSERT INTO dvrmetrics (driverid, stopspeed, packagespeed, stopspeedscore, packagespeedscore) VALUES ('" + result.getString("driverid") + "', '" + result.getString("AVG(stopspeed)") + "', '" + result.getString("AVG(packagespeed)") + "', '" + stopspeedscore + "', '" + packagespeedscore + "')";
            Database.commitToDB(pushStatement);
        }
    }

    public static void rescueRatiosQueryA() throws SQLException {
        clearTable("rescueratiosa");
        String rescuesForStatement = "SELECT saviorid, SUM(stops) FROM rescues GROUP BY saviorid";
        ResultSet rescuesForResult = Database.pullFromDB(rescuesForStatement);
        String driverid = "UNDETERMINED";
        double rescuesFor = 0;
        double rescuesAgainst = 0;
        double rescueRatio = 0;
        while (true) {
            assert rescuesForResult != null;
            if (!rescuesForResult.next()) break;
            driverid = rescuesForResult.getString("saviorid");
            rescuesFor = Double.parseDouble(rescuesForResult.getString("SUM(stops)"));
            String rescuesAgainstStatement = "SELECT subjectid, SUM(stops) FROM rescues WHERE subjectid = '" + driverid + "' GROUP BY subjectid";
            ResultSet rescuesAgainstResult = Database.pullFromDB(rescuesAgainstStatement);
            while (true) {
                assert rescuesAgainstResult != null;
                if (!rescuesAgainstResult.next()) break;
                rescuesAgainst = Double.parseDouble(rescuesAgainstResult.getString("SUM(stops)"));
                if (rescuesAgainst == 0) {
                    rescueRatio = rescuesFor;
                } else {
                    rescueRatio = rescuesFor / rescuesAgainst;
                }
                String pushStatement = "INSERT INTO rescueratiosa (driverid, rescuesfor, rescuesagainst, rescueratio) VALUES ('" + driverid + "', '" + rescuesFor + "', '" + rescuesAgainst + "', '" + rescueRatio + "')";
                Database.commitToDB(pushStatement);
            }
        }
    }

    public static void rescueRatiosQueryB() throws SQLException {
        clearTable("rescueratiosb");
        String dspRescuesStatement = "SELECT AVG(rescueratio) FROM rescueratiosa";
        ResultSet dspRescuesResult = Database.pullFromDB(dspRescuesStatement);
        double dspAvgRescueRatio = 0;
        while (true) {
            assert dspRescuesResult != null;
            if (!dspRescuesResult.next()) break;
            try {
                dspAvgRescueRatio = Double.parseDouble(dspRescuesResult.getString("AVG(rescueratio)"));
            } catch (NullPointerException e) {
                dspAvgRescueRatio = 1;
            }
        }
        String pullDriversStatement = "SELECT * FROM drivers";
        ResultSet driversResult = Database.pullFromDB(pullDriversStatement);
        while (true) {
            assert driversResult != null;
            if (!driversResult.next()) break;
            String rescuesStatement = "SELECT driverid, rescuesfor, rescuesagainst, rescueratio FROM rescueratiosa WHERE driverid = '" + driversResult.getString("driverid") + "'";
            ResultSet rescuesResult = Database.pullFromDB(rescuesStatement);
            double rescueRatioScore = 100;
            while (true) {
                assert rescuesResult != null;
                if (!rescuesResult.next()) {
                    String pushStatement = "INSERT INTO rescueratiosb (driverid, rescuesfor, rescuesagainst, rescueratio, rescueratioscore) VALUES ('" + driversResult.getString("driverid") + "', '0', '0', '0', '" + rescueRatioScore + "')";
                    Database.commitToDB(pushStatement);
                    break;
                }
                rescueRatioScore = (1 + (Double.parseDouble(rescuesResult.getString("rescueratio")) - dspAvgRescueRatio) / dspAvgRescueRatio) * 100 ;
                String pushStatement = "INSERT INTO rescueratiosb (driverid, rescuesfor, rescuesagainst, rescueratio, rescueratioscore) VALUES ('" + rescuesResult.getString("driverid") + "', '" + rescuesResult.getString("rescuesfor") + "', '" + rescuesResult.getString("rescuesagainst") + "', '" + rescuesResult.getString("rescueratio") + "', '" + rescueRatioScore + "')";
                Database.commitToDB(pushStatement);
            }
        }
    }

    public static void rescueRatiosQueryB_Archive() throws SQLException {
        String rescuesStatement = "SELECT driverid, rescuesfor, rescuesagainst, rescueratio FROM rescueratiosa";
        ResultSet rescuesResult = Database.pullFromDB(rescuesStatement);
        String dspRescuesStatement = "SELECT AVG(rescueratio) FROM rescueratiosa";
        ResultSet dspRescuesResult = Database.pullFromDB(dspRescuesStatement);
        double dspAvgRescueRatio = 0;
        while (true) {
            assert dspRescuesResult != null;
            if (!dspRescuesResult.next()) break;
            try {
                dspAvgRescueRatio = Double.parseDouble(dspRescuesResult.getString("AVG(rescueratio)"));
            } catch (NullPointerException e) {
                dspAvgRescueRatio = 1;
            }
        }
        double rescueRatioScore = 100;
        while (true) {
            assert rescuesResult != null;
            if (!rescuesResult.next()) break;
            rescueRatioScore = (1 + (Double.parseDouble(rescuesResult.getString("rescueratio")) - dspAvgRescueRatio) / dspAvgRescueRatio) * 100 ;
            String pushStatement = "INSERT INTO rescueratiosb (driverid, rescuesfor, rescuesagainst, rescueratio, rescueratioscore) VALUES ('" + rescuesResult.getString("driverid") + "', '" + rescuesResult.getString("rescuesfor") + "', '" + rescuesResult.getString("rescuesagainst") + "', '" + rescuesResult.getString("rescueratio") + "', '" + rescueRatioScore + "')";
            Database.commitToDB(pushStatement);
        }
    }

    public static void coreScoreQuery() throws SQLException {
        clearTable("scores");
        String pullStatement = "SELECT driverid, driverinitials, drivername, stopspeed, packagespeed, stopspeedscore, packagespeedscore, rescuesfor, rescuesagainst, rescueratio, rescueratioscore, attendancescore, markscore FROM canopy.drivers INNER JOIN canopy.dvrmetrics USING (driverid) INNER JOIN canopy.rescueratiosb  USING (driverid) INNER JOIN canopy.attendance  USING (driverid) INNER JOIN canopy.marks  USING (driverid) GROUP BY driverid;";
        ResultSet pullResult = Database.pullFromDB(pullStatement);
        while (true) {
            assert pullResult != null;
            if (!pullResult.next()) break;
            double canopyScore = (Double.parseDouble(pullResult.getString("stopspeedscore")) * 0.25) + (Double.parseDouble(pullResult.getString("rescueratioscore")) * 0.25) + (Double.parseDouble(pullResult.getString("attendancescore")) * 0.25) + (Double.parseDouble(pullResult.getString("markscore")) * 0.25);
            String pushStatement = "INSERT INTO scores (driverid, driverinitials, drivername," +
                    " stopspeed, packagespeed, stopspeedscore, packagespeedscore, rescuesfor," +
                    " rescuesagainst, rescueratio, rescueratioscore, attendancescore, markscore," +
                    " canopyscore) VALUES ('" + pullResult.getString("driverid") +
                    "', '" + pullResult.getString("driverinitials") + "', '" + pullResult.getString("drivername") +
                    "', '" + pullResult.getString("stopspeed") + "', '" + pullResult.getString("packagespeed") +
                    "', '" + pullResult.getString("stopspeedscore") + "', '" + pullResult.getString("packagespeedscore") + "', '" + pullResult.getString("rescuesfor") +
                    "', '" + pullResult.getString("rescuesagainst") + "', '" + pullResult.getString("rescueratio") +
                    "', '" + pullResult.getString("rescueratioscore") + "', '" + pullResult.getString("attendancescore") +
                    "', '" + pullResult.getString("markscore") + "', '" + canopyScore + "')";
            Database.commitToDB(pushStatement);
        }
    }




    public static String matchDriverNameToId(String driverName) throws SQLException {
        String driverId = "UNDETERMINED";
        String statement = "SELECT driverid FROM drivers WHERE drivername='" + driverName + "'";
        ResultSet result = Database.pullFromDB(statement);
        while (true) {
            assert result != null;
            if (!result.next()) break;
            driverId = result.getString("driverid");
        }
        return driverId;
    }

    public static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }
}

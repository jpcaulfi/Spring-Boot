package components;

import java.sql.*;

public class Database {

    public static Connection getDBConnection() throws Exception {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/** OMITTED **";
            String username = "** OMITTED **";
            String password = "** OMITTED **";
            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public static void commitToDB(String statement) {
        try {
            Connection conn = getDBConnection();
            assert conn != null;
            PreparedStatement posted = conn.prepareStatement(statement);
            posted.executeUpdate();
        } catch(Exception e){
            //System.out.println(e);
        }
    }

    public static ResultSet pullFromDB(String statement) {
        try {
            Connection conn = getDBConnection();
            assert conn != null;
            PreparedStatement posted = conn.prepareStatement(statement);
            return posted.executeQuery();
        } catch(Exception e){
            //System.out.println(e);
            return null;
        }
    }

    public static void primeDB() throws SQLException {
        primeAttendance();
        primeMarks();
    }

    public static void primeAttendance() throws SQLException {
        clearTable("attendance");
        String driverStatement = "SELECT * FROM drivers";
        ResultSet driverResult = Database.pullFromDB(driverStatement);
        while (true) {
            assert driverResult != null;
            if (!driverResult.next()) break;
            String attendanceStatement = "SELECT driverid FROM attendance WHERE driverid = '" + driverResult.getString("driverid") + "'";
            ResultSet attendanceResult = Database.pullFromDB(attendanceStatement);
            while (true) {
                assert attendanceResult != null;
                if (!attendanceResult.next()) {
                    Attendance thisAttendance = new Attendance(driverResult.getString("driverid"), "SET_SCORE", "SET_SCORE", 100);
                    thisAttendance.insert();
                    break;
                }
            }
        }
    }

    public static void primeMarks() throws SQLException {
        clearTable("marks");
        String driverStatement = "SELECT * FROM drivers";
        ResultSet driverResult = Database.pullFromDB(driverStatement);
        while (true) {
            assert driverResult != null;
            if (!driverResult.next()) break;
            String markStatement = "SELECT driverid FROM marks WHERE driverid = '" + driverResult.getString("driverid") + "'";
            ResultSet markResult = Database.pullFromDB(markStatement);
            while (true) {
                assert markResult != null;
                if (!markResult.next()) {
                    Mark thisMark = new Mark(driverResult.getString("driverid"), "SET_SCORE", "SET_SCORE", 100);
                    thisMark.insert();
                    break;
                }
            }
        }
    }

    public static void restartDB() {
        //clearTable("webhooks");
        //clearTable("drivers");
        //clearTable("routes");
        //clearTable("runs");
        //clearTable("metrics");
        clearTable("attendance");
        clearTable("marks");
        //clearTable("rescues");
        clearTable("routemetricsa");
        clearTable("routemetricsb");
        clearTable("drivermetricsa");
        clearTable("drivermetricsb");
        clearTable("dvrmetrics");
        clearTable("rescueratiosa");
        clearTable("rescueratiosb");
        clearTable("scores");
    }

    public static void clearTable(String table) {
        String statement = "DELETE FROM " + table;
        Database.commitToDB(statement);
    }
}

package components;

public class Attendance {

    String driverId;
    String date;
    String type;
    double score;

    public Attendance(String driverId, String date, String type, double score) {

        this.driverId = driverId;
        this.date = date;
        this.type = type;
        this.score = score;
    }

    public String getDriverId() {
        return driverId;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public double getScore() {
        return score;
    }

    public void insert() {
        String statement = "INSERT INTO attendance (driverid, date, type, attendancescore) VALUES ('"+this.driverId+"', '"+this.date+"', '"+this.type+"', '"+this.score+"')";
        Database.commitToDB(statement);
    }
}

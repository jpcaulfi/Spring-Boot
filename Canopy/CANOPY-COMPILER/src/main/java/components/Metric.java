package components;

public class Metric {

    String driverId;
    String routeId;
    String date;
    String time;
    int stopsDone;
    int packagesDone;

    public Metric(String driverId, String routeId, String date, String time, int stopsDone, int packagesDone) {
        this.driverId = driverId;
        this.routeId = routeId;
        this.date = date;
        this.time = time;
        this.stopsDone = stopsDone;
        this.packagesDone = packagesDone;
    }

    public String getDriverId() {
        return driverId;
    }

    public String getRouteId() {
        return routeId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getStopsDone() {
        return stopsDone;
    }

    public int getPackagesDone() {
        return packagesDone;
    }

    public void insert() {
        String statement = "INSERT INTO metrics (driverid, routeid, date, time, stopsdone, packagesdone) VALUES ('"+this.driverId+"', '"+this.routeId+"', '"+this.date+"', '"+this.time+"', '"+this.stopsDone+"', '"+this.packagesDone+"')";
        Database.commitToDB(statement);
    }
}

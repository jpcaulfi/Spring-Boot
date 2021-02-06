package components;

public class Run {

    String routeId;
    String driverId;
    int packages;
    int stops;
    String date;
    String owner;

    public Run(String routeId, String driverId, int packages, int stops, String date, String owner) {
        this.routeId = routeId;
        this.driverId = driverId;
        this.packages = packages;
        this.stops = stops;
        this.date = date;
        this.owner = owner;
    }

    public void insert() {
        String statement = "INSERT INTO runs (routeid, driverid, packages, stops, date, owner) VALUES ('"+routeId+"', '"+driverId+"', '"+packages+"', '"+stops+"', '"+date+"', '"+owner+"')";
        Database.commitToDB(statement);
    }

    public String getRouteId() {
        return routeId;
    }

    public String getDriverId() {
        return driverId;
    }

    public int getPackages() {
        return packages;
    }

    public int getStops() {
        return stops;
    }

    public String getDate() {
        return date;
    }

    public String getOwner() {
        return owner;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public void setPackages(int packages) {
        this.packages = packages;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}

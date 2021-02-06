package components;

public class Driver {

    String driverId;
    String driverInitials;
    String driverName;

    public Driver(String driverId, String driverInitials, String driverName) throws Exception {
        this.driverId = driverId;
        this.driverInitials = driverInitials;
        this.driverName = driverName;
    }

    public String getDriverId() {
        return driverId;
    }

    public String getDriverInitials() {
        return driverInitials;
    }

    public String getDriverName() {
        return driverName;
    }

    public void insert() {
        String statement = "INSERT INTO drivers (driverid, driverinitials, drivername) VALUES ('"+this.driverId+"', '"+this.driverInitials+"', '"+this.driverName+"')";
        Database.commitToDB(statement);
    }
}

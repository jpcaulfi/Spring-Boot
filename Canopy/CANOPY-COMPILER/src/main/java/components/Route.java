package components;

public class Route {

    String routeId;
    String routeCode;
    String location;

    public Route(String routeId, String routeCode, String location) {
        this.routeId = routeId;
        this.routeCode = routeCode;
        this.location = location;
    }

    public void insert() {
        String statement = "INSERT INTO routes (routeid, routecode, location) VALUES ('"+this.routeId+"', '"+this.routeCode+"', '"+this.location+"')";
        Database.commitToDB(statement);
    }

    public String getRouteId() {
        return routeId;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public String getLocation() {
        return location;
    }
}

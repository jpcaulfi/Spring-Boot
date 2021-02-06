package components;

public class Rescue {

    int rescueId;
    String date;
    String time;
    String routeId;
    String saviorId;
    String subjectId;
    double stops;
    double packages;

    public Rescue(String date, String time, String routeId, String saviorId, String subjectId, double stops, double packages) {
        this.rescueId = -1;
        this.date = date;
        this.time = time;
        this.routeId = routeId;
        this.saviorId = saviorId;
        this.subjectId = subjectId;
        this.stops = stops;
        this.packages = packages;
    }

    public int getRescueId() {
        return rescueId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getRouteId() {
        return routeId;
    }

    public String getSaviorId() {
        return saviorId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public double getStops() {
        return stops;
    }

    public double getPackages() {
        return packages;
    }

    public void insert() {
        String statement = "INSERT INTO rescues (rescueid, date, time, routeid, saviorid, subjectid, stops, packages) VALUES (NULL, '"+this.date+"', '"+this.time+"', '"+this.routeId+"', '"+this.saviorId+"', '"+this.subjectId+"', '"+this.stops+"', '"+this.packages+"')";
        Database.commitToDB(statement);
    }

}

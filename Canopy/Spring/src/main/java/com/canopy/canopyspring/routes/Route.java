package com.canopy.canopyspring.routes;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "routes")
public class Route {

    @Id
    @Column(name = "routeid")
    private Long id;

    @Column(name = "routeidstring")
    private String routeIdString;

    @Column(name = "routecode")
    private String routeCode;

    @Column(name = "location")
    private String location;

    public Route() {

    }

    public Route(Long id, String routeIdString, String routeCode, String location) {
        this.id = id;
        this.routeIdString = routeIdString;
        this.routeCode = routeCode;
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return id.equals(route.id) && routeIdString.equals(route.routeIdString) && routeCode.equals(route.routeCode) && location.equals(route.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, routeIdString, routeCode, location);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRouteIdString() {
        return routeIdString;
    }

    public void setRouteIdString(String routeIdString) {
        this.routeIdString = routeIdString;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", routeIdString='" + routeIdString + '\'' +
                ", routeCode='" + routeCode + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}

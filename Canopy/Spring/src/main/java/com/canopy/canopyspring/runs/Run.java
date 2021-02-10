package com.canopy.canopyspring.runs;

import com.canopy.canopyspring.drivers.Driver;
import com.canopy.canopyspring.routes.Route;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "runs")
public class Run {

    @Id
    @Column(name = "runid")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "routeid")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "driverid")
    private Driver driver;

    @Column(name = "packages")
    private int packages;

    @Column(name = "stops")
    private int stops;

    @Column(name = "date")
    private String date;

    @Column(name = "owner")
    private String owner;

    public Run() {

    }

    public Run(Long id, Route route, Driver driver, int packages, int stops, String date, String owner) {
        this.id = id;
        this.route = route;
        this.driver = driver;
        this.packages = packages;
        this.stops = stops;
        this.date = date;
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Run run = (Run) o;
        return packages == run.packages && stops == run.stops && id.equals(run.id) && route.equals(run.route) && driver.equals(run.driver) && date.equals(run.date) && owner.equals(run.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, route, driver, packages, stops, date, owner);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public int getPackages() {
        return packages;
    }

    public void setPackages(int packages) {
        this.packages = packages;
    }

    public int getStops() {
        return stops;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Run{" +
                "id=" + id +
                ", route=" + route +
                ", driver=" + driver +
                ", packages=" + packages +
                ", stops=" + stops +
                ", date='" + date + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}

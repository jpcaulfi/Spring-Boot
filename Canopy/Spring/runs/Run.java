package com.canopy.canopyspring.runs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "simruns")
public class Run {

    @Id
    @Column(name = "simrunid")
    private Long id;

    @Column(name = "routeid")
    private Long routeId;

    @Column(name = "driverid")
    private Long driverId;

    @Column(name = "routecode")
    private String routeCode;

    @Column(name = "driverinitials")
    private String driverInitials;

    @Column(name = "stopsleft")
    private int stopsLeft;

    @Column(name = "packagesleft")
    private int packagesLeft;

    @Column(name = "currentcompletion")
    private double currentCompletion;

    @Column(name = "targetcompletion")
    private double targetCompletion;

    @Column(name = "delta")
    private double delta;

    public Run() {
    }

    public Run(Long id, Long routeId, Long driverId, String routeCode, String driverInitials, int stopsLeft, int packagesLeft, double currentCompletion, double targetCompletion, double delta) {
        this.id = id;
        this.routeId = routeId;
        this.driverId = driverId;
        this.routeCode = routeCode;
        this.driverInitials = driverInitials;
        this.stopsLeft = stopsLeft;
        this.packagesLeft = packagesLeft;
        this.currentCompletion = currentCompletion;
        this.targetCompletion = targetCompletion;
        this.delta = delta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Run run = (Run) o;
        return stopsLeft == run.stopsLeft && packagesLeft == run.packagesLeft && Double.compare(run.currentCompletion, currentCompletion) == 0 && Double.compare(run.targetCompletion, targetCompletion) == 0 && Double.compare(run.delta, delta) == 0 && Objects.equals(id, run.id) && Objects.equals(routeId, run.routeId) && Objects.equals(driverId, run.driverId) && Objects.equals(routeCode, run.routeCode) && Objects.equals(driverInitials, run.driverInitials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, routeId, driverId, routeCode, driverInitials, stopsLeft, packagesLeft, currentCompletion, targetCompletion, delta);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode;
    }

    public String getDriverInitials() {
        return driverInitials;
    }

    public void setDriverInitials(String driverInitials) {
        this.driverInitials = driverInitials;
    }

    public int getStopsLeft() {
        return stopsLeft;
    }

    public void setStopsLeft(int stopsLeft) {
        this.stopsLeft = stopsLeft;
    }

    public int getPackagesLeft() {
        return packagesLeft;
    }

    public void setPackagesLeft(int packagesLeft) {
        this.packagesLeft = packagesLeft;
    }

    public double getCurrentCompletion() {
        return currentCompletion;
    }

    public void setCurrentCompletion(double currentCompletion) {
        this.currentCompletion = currentCompletion;
    }

    public double getTargetCompletion() {
        return targetCompletion;
    }

    public void setTargetCompletion(double targetCompletion) {
        this.targetCompletion = targetCompletion;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    @Override
    public String toString() {
        return "Run{" +
                "id=" + id +
                ", routeId=" + routeId +
                ", driverId=" + driverId +
                ", routeCode='" + routeCode + '\'' +
                ", driverInitials='" + driverInitials + '\'' +
                ", stopsLeft=" + stopsLeft +
                ", packagesLeft=" + packagesLeft +
                ", currentCompletion=" + currentCompletion +
                ", targetCompletion=" + targetCompletion +
                ", delta=" + delta +
                '}';
    }
}

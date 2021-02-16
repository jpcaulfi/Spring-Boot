package com.canopy.canopyspring.rescues;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "simrescues")
public class Rescue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "simrescueid")
    private Long id;

    @Column(name = "routeid")
    private Long routeId;

    @Column(name = "routecode")
    private String routeCode;

    @Column(name = "saviorid")
    private Long saviorId;

    @Column(name = "saviorinitials")
    private String saviorInitials;

    @Column(name = "subjectid")
    private Long subjectId;

    @Column(name = "subjectinitials")
    private String subjectInitials;

    @Column(name = "stops")
    private int stops;

    public Rescue() {
    }

    public Rescue(Long id, Long routeId, String routeCode, Long saviorId, String saviorInitials, Long subjectId, String subjectInitials, int stops) {
        this.id = id;
        this.routeId = routeId;
        this.routeCode = routeCode;
        this.saviorId = saviorId;
        this.saviorInitials = saviorInitials;
        this.subjectId = subjectId;
        this.subjectInitials = subjectInitials;
        this.stops = stops;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rescue rescue = (Rescue) o;
        return stops == rescue.stops && Objects.equals(id, rescue.id) && Objects.equals(routeId, rescue.routeId) && Objects.equals(routeCode, rescue.routeCode) && Objects.equals(saviorId, rescue.saviorId) && Objects.equals(saviorInitials, rescue.saviorInitials) && Objects.equals(subjectId, rescue.subjectId) && Objects.equals(subjectInitials, rescue.subjectInitials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, routeId, routeCode, saviorId, saviorInitials, subjectId, subjectInitials, stops);
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

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode;
    }

    public Long getSaviorId() {
        return saviorId;
    }

    public void setSaviorId(Long saviorId) {
        this.saviorId = saviorId;
    }

    public String getSaviorInitials() {
        return saviorInitials;
    }

    public void setSaviorInitials(String saviorInitials) {
        this.saviorInitials = saviorInitials;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectInitials() {
        return subjectInitials;
    }

    public void setSubjectInitials(String subjectInitials) {
        this.subjectInitials = subjectInitials;
    }

    public int getStops() {
        return stops;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }

    @Override
    public String toString() {
        return "Rescue{" +
                "id=" + id +
                ", routeId=" + routeId +
                ", routeCode='" + routeCode + '\'' +
                ", saviorId=" + saviorId +
                ", saviorInitials='" + saviorInitials + '\'' +
                ", subjectId=" + subjectId +
                ", subjectInitials='" + subjectInitials + '\'' +
                ", stops=" + stops +
                '}';
    }
}

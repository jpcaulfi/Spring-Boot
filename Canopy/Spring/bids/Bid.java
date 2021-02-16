package com.canopy.canopyspring.bids;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "simbids")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "simbidid")
    private Long id;

    @Column(name = "routeid")
    private Long routeId;

    @Column(name = "routecode")
    private String routeCode;

    @Column(name = "subjectid")
    private Long subjectId;

    @Column(name = "subjectinitials")
    private String subjectInitials;

    @Column(name = "stops")
    private int stops;

    public Bid() {
    }

    public Bid(Long id, Long routeId, String routeCode, Long subjectId, String subjectInitials, int stops) {
        this.id = id;
        this.routeId = routeId;
        this.routeCode = routeCode;
        this.subjectId = subjectId;
        this.subjectInitials = subjectInitials;
        this.stops = stops;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bid bid = (Bid) o;
        return stops == bid.stops && Objects.equals(id, bid.id) && Objects.equals(routeId, bid.routeId) && Objects.equals(routeCode, bid.routeCode) && Objects.equals(subjectId, bid.subjectId) && Objects.equals(subjectInitials, bid.subjectInitials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, routeId, routeCode, subjectId, subjectInitials, stops);
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
        return "Bid{" +
                "id=" + id +
                ", routeId=" + routeId +
                ", routeCode='" + routeCode + '\'' +
                ", subjectId=" + subjectId +
                ", subjectInitials='" + subjectInitials + '\'' +
                ", stops=" + stops +
                '}';
    }
}

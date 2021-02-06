package com.canopyv5.CANOPYV5.rescuesupforbid;

import java.time.LocalDate;
import java.time.LocalTime;

public class RescueUpForBid {

    int rescueId;
    LocalDate date;
    LocalTime time;
    String routeId;
    String subjectId;
    int stops;

    public RescueUpForBid(int rescueId, LocalDate date, LocalTime time, String routeId, String subjectId, int stops) {
        this.rescueId = rescueId;
        this.date = date;
        this.time = time;
        this.routeId = routeId;
        this.subjectId = subjectId;
        this.stops = stops;
    }

    public int getRescueId() {
        return rescueId;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getRouteId() {
        return routeId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public int getStops() {
        return stops;
    }
}

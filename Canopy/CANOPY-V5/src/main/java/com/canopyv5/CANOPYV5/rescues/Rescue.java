package com.canopyv5.CANOPYV5.rescues;

import java.time.LocalDate;
import java.time.LocalTime;

public class Rescue {

    int rescueId;
    LocalDate date;
    LocalTime time;
    String routeId;
    String saviorId;
    String subjectId;
    int stops;

    public Rescue(int rescueId, LocalDate date, LocalTime time, String routeId, String saviorId, String subjectId, int stops) {
        this.rescueId = rescueId;
        this.date = date;
        this.time = time;
        this.routeId = routeId;
        this.saviorId = saviorId;
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

    public String getSaviorId() {
        return saviorId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public int getStops() {
        return stops;
    }

    @Override
    public String toString() {
        return "Rescue{" +
                "rescueId=" + rescueId +
                ", date=" + date +
                ", time=" + time +
                ", routeId='" + routeId + '\'' +
                ", saviorId='" + saviorId + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", stops=" + stops +
                '}';
    }
}

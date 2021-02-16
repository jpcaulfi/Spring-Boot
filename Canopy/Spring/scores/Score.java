package com.canopy.canopyspring.scores;

import com.canopy.canopyspring.drivers.Driver;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "scores")
public class Score {

    @Id
    @Column(name = "scoreid")
    private Long id;

    @Column(name = "driverid")
    private Long driverId;

    @Column(name = "driverinitials")
    private String driverInitials;

    @Column(name = "drivername")
    private String driverName;

    @Column(name = "stopspeed")
    private double stopSpeed;

    @Column(name = "packagespeed")
    private double packageSpeed;

    @Column(name = "stopspeedscore")
    private double stopSpeedScore;

    @Column(name = "packagespeedscore")
    private double packageSpeedScore;

    @Column(name = "rescuesfor")
    private int rescuesFor;

    @Column(name = "rescuesagainst")
    private int rescuesAgainst;

    @Column(name = "rescueratio")
    private double rescueRatio;

    @Column(name = "rescueratioscore")
    private double rescueRatioScore;

    @Column(name = "attendancescore")
    private double attendanceScore;

    @Column(name = "markscore")
    private double markScore;

    @Column(name = "canopyscore")
    private double canopyScore;

    public Score() {

    }

    public Score(Long id, Long driverId, String driverInitials, String driverName, double stopSpeed, double packageSpeed, double stopSpeedScore, double packageSpeedScore, int rescuesFor, int rescuesAgainst, double rescueRatio, double rescueRatioScore, double attendanceScore, double markScore, double canopyScore) {
        this.id = id;
        this.driverId = driverId;
        this.driverInitials = driverInitials;
        this.driverName = driverName;
        this.stopSpeed = stopSpeed;
        this.packageSpeed = packageSpeed;
        this.stopSpeedScore = stopSpeedScore;
        this.packageSpeedScore = packageSpeedScore;
        this.rescuesFor = rescuesFor;
        this.rescuesAgainst = rescuesAgainst;
        this.rescueRatio = rescueRatio;
        this.rescueRatioScore = rescueRatioScore;
        this.attendanceScore = attendanceScore;
        this.markScore = markScore;
        this.canopyScore = canopyScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return Double.compare(score.stopSpeed, stopSpeed) == 0 && Double.compare(score.packageSpeed, packageSpeed) == 0 && Double.compare(score.stopSpeedScore, stopSpeedScore) == 0 && Double.compare(score.packageSpeedScore, packageSpeedScore) == 0 && rescuesFor == score.rescuesFor && rescuesAgainst == score.rescuesAgainst && Double.compare(score.rescueRatio, rescueRatio) == 0 && Double.compare(score.rescueRatioScore, rescueRatioScore) == 0 && Double.compare(score.attendanceScore, attendanceScore) == 0 && Double.compare(score.markScore, markScore) == 0 && Double.compare(score.canopyScore, canopyScore) == 0 && Objects.equals(id, score.id) && Objects.equals(driverId, score.driverId) && Objects.equals(driverInitials, score.driverInitials) && Objects.equals(driverName, score.driverName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, driverId, driverInitials, driverName, stopSpeed, packageSpeed, stopSpeedScore, packageSpeedScore, rescuesFor, rescuesAgainst, rescueRatio, rescueRatioScore, attendanceScore, markScore, canopyScore);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getDriverInitials() {
        return driverInitials;
    }

    public void setDriverInitials(String driverInitials) {
        this.driverInitials = driverInitials;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public double getStopSpeed() {
        return stopSpeed;
    }

    public void setStopSpeed(double stopSpeed) {
        this.stopSpeed = stopSpeed;
    }

    public double getPackageSpeed() {
        return packageSpeed;
    }

    public void setPackageSpeed(double packageSpeed) {
        this.packageSpeed = packageSpeed;
    }

    public double getStopSpeedScore() {
        return stopSpeedScore;
    }

    public void setStopSpeedScore(double stopSpeedScore) {
        this.stopSpeedScore = stopSpeedScore;
    }

    public double getPackageSpeedScore() {
        return packageSpeedScore;
    }

    public void setPackageSpeedScore(double packageSpeedScore) {
        this.packageSpeedScore = packageSpeedScore;
    }

    public int getRescuesFor() {
        return rescuesFor;
    }

    public void setRescuesFor(int rescuesFor) {
        this.rescuesFor = rescuesFor;
    }

    public int getRescuesAgainst() {
        return rescuesAgainst;
    }

    public void setRescuesAgainst(int rescuesAgainst) {
        this.rescuesAgainst = rescuesAgainst;
    }

    public double getRescueRatio() {
        return rescueRatio;
    }

    public void setRescueRatio(double rescueRatio) {
        this.rescueRatio = rescueRatio;
    }

    public double getRescueRatioScore() {
        return rescueRatioScore;
    }

    public void setRescueRatioScore(double rescueRatioScore) {
        this.rescueRatioScore = rescueRatioScore;
    }

    public double getAttendanceScore() {
        return attendanceScore;
    }

    public void setAttendanceScore(double attendanceScore) {
        this.attendanceScore = attendanceScore;
    }

    public double getMarkScore() {
        return markScore;
    }

    public void setMarkScore(double markScore) {
        this.markScore = markScore;
    }

    public double getCanopyScore() {
        return canopyScore;
    }

    public void setCanopyScore(double canopyScore) {
        this.canopyScore = canopyScore;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", driverId=" + driverId +
                ", driverInitials='" + driverInitials + '\'' +
                ", driverName='" + driverName + '\'' +
                ", stopSpeed=" + stopSpeed +
                ", packageSpeed=" + packageSpeed +
                ", stopSpeedScore=" + stopSpeedScore +
                ", packageSpeedScore=" + packageSpeedScore +
                ", rescuesFor=" + rescuesFor +
                ", rescuesAgainst=" + rescuesAgainst +
                ", rescueRatio=" + rescueRatio +
                ", rescueRatioScore=" + rescueRatioScore +
                ", attendanceScore=" + attendanceScore +
                ", markScore=" + markScore +
                ", canopyScore=" + canopyScore +
                '}';
    }
}

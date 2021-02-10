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

    @OneToOne(mappedBy = "score")
    private Driver driver;

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

    public Score(Long id, Driver driver, double stopSpeed, double packageSpeed, double stopSpeedScore, double packageSpeedScore, int rescuesFor, int rescuesAgainst, double rescueRatio, double rescueRatioScore, double attendanceScore, double markScore, double canopyScore) {
        this.id = id;
        this.driver = driver;
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
        return Double.compare(score.stopSpeed, stopSpeed) == 0 && Double.compare(score.packageSpeed, packageSpeed) == 0 && Double.compare(score.stopSpeedScore, stopSpeedScore) == 0 && Double.compare(score.packageSpeedScore, packageSpeedScore) == 0 && rescuesFor == score.rescuesFor && rescuesAgainst == score.rescuesAgainst && Double.compare(score.rescueRatio, rescueRatio) == 0 && Double.compare(score.rescueRatioScore, rescueRatioScore) == 0 && Double.compare(score.attendanceScore, attendanceScore) == 0 && Double.compare(score.markScore, markScore) == 0 && Double.compare(score.canopyScore, canopyScore) == 0 && Objects.equals(id, score.id) && Objects.equals(driver, score.driver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, driver, stopSpeed, packageSpeed, stopSpeedScore, packageSpeedScore, rescuesFor, rescuesAgainst, rescueRatio, rescueRatioScore, attendanceScore, markScore, canopyScore);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
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
                ", driver=" + driver +
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

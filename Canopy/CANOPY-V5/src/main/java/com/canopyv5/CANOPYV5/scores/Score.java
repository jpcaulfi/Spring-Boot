package com.canopyv5.CANOPYV5.scores;

public class Score {

    String driverId;
    String driverInitials;
    String driverName;
    double stopSpeed;
    double packageSpeed;
    double stopSpeedScore;
    double packageSpeedScore;
    double rescuesFor;
    double rescuesAgainst;
    double rescueRatio;
    double rescueRatioScore;
    double attendanceScore;
    double markScore;
    double canopyScore;

    public Score(String driverId, String driverInitials, String driverName, double stopSpeed, double packageSpeed, double stopSpeedScore, double packageSpeedScore, double rescuesFor, double rescuesAgainst, double rescueRatio, double rescueRatioScore, double attendanceScore, double markScore, double canopyScore) {
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

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
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

    public double getRescuesFor() {
        return rescuesFor;
    }

    public void setRescuesFor(double rescuesFor) {
        this.rescuesFor = rescuesFor;
    }

    public double getRescuesAgainst() {
        return rescuesAgainst;
    }

    public void setRescuesAgainst(double rescuesAgainst) {
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
                "driverId='" + driverId + '\'' +
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

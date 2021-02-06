package components;

public class RawData {

    String urlKey;
    String url;
    String date;
    String time;
    String hour;
    String minute;
    int countOfTotalStopsR;
    int countOfCompletedStopsR;
    String routeId;
    String routeCode;
    String companyId;
    int countTotal;
    int countDelivered;
    int countAttempted;
    int countReturning;
    int countDeliveredAndAttempted;
    double currentCompletion;
    String transporterId;
    String transporterName;
    String id;
    String name;
    int numberOfPackages;
    String plannedDepartureTime;
    String serviceTypeName;
    String driverId;
    int countOfAttemptedPackages;
    int countOfCompletedPackages;
    int countOfCompletedStops;
    int countOfDeliveredPackages;
    int countOfMissingPackages;
    int countOfReattemptablePackages;
    int countOfRemainingPackages;
    int countOfReturnedPackages;
    int countOfReturningPackages;
    int countOfTotalPackages;
    int countOfTotalStops;
    int countOfUndeliverablePackages;
    int packagesAhead;
    int packagesBehind;
    String riskModelUsed;
    String riskStatus;
    int stopsAhead;
    int stopsBehind;
    String kittRiskStatus;
    String startTime;
    String departureTime;
    String calculationTime;
    double initialHour;
    double packageRate;
    double delta;
    int remainingStops;
    double targetCompletion;
    int timeLeft;
    String departureEndTime;
    double stopsRate;

    public RawData(String urlKey, String url, String date, String time, String hour, String minute, int countOfTotalStopsR, int countOfCompletedStopsR, String routeId, String routeCode, String companyId, int countTotal, int countDelivered, int countAttempted, int countReturning, int countDeliveredAndAttempted, double currentCompletion, String transporterId, String transporterName, String id, String name, int numberOfPackages, String plannedDepartureTime, String serviceTypeName, String driverId, int countOfAttemptedPackages, int countOfCompletedPackages, int countOfCompletedStops, int countOfDeliveredPackages, int countOfMissingPackages, int countOfReattemptablePackages, int countOfRemainingPackages, int countOfReturnedPackages, int countOfReturningPackages, int countOfTotalPackages, int countOfTotalStops, int countOfUndeliverablePackages, int packagesAhead, int packagesBehind, String riskModelUsed, String riskStatus, int stopsAhead, int stopsBehind, String kittRiskStatus, String startTime, String departureTime, String calculationTime, double initialHour, double packageRate, double delta, int remainingStops, double targetCompletion, int timeLeft, String departureEndTime, double stopsRate) {
        this.urlKey = urlKey;
        this.url = url;
        this.date = date;
        this.time = time;
        this.hour = hour;
        this.minute = minute;
        this.countOfTotalStopsR = countOfTotalStopsR;
        this.countOfCompletedStopsR = countOfCompletedStopsR;
        this.routeId = routeId;
        this.routeCode = routeCode;
        this.companyId = companyId;
        this.countTotal = countTotal;
        this.countDelivered = countDelivered;
        this.countAttempted = countAttempted;
        this.countReturning = countReturning;
        this.countDeliveredAndAttempted = countDeliveredAndAttempted;
        this.currentCompletion = currentCompletion;
        this.transporterId = transporterId;
        this.transporterName = transporterName;
        this.id = id;
        this.name = name;
        this.numberOfPackages = numberOfPackages;
        this.plannedDepartureTime = plannedDepartureTime;
        this.serviceTypeName = serviceTypeName;
        this.driverId = driverId;
        this.countOfAttemptedPackages = countOfAttemptedPackages;
        this.countOfCompletedPackages = countOfCompletedPackages;
        this.countOfCompletedStops = countOfCompletedStops;
        this.countOfDeliveredPackages = countOfDeliveredPackages;
        this.countOfMissingPackages = countOfMissingPackages;
        this.countOfReattemptablePackages = countOfReattemptablePackages;
        this.countOfRemainingPackages = countOfRemainingPackages;
        this.countOfReturnedPackages = countOfReturnedPackages;
        this.countOfReturningPackages = countOfReturningPackages;
        this.countOfTotalPackages = countOfTotalPackages;
        this.countOfTotalStops = countOfTotalStops;
        this.countOfUndeliverablePackages = countOfUndeliverablePackages;
        this.packagesAhead = packagesAhead;
        this.packagesBehind = packagesBehind;
        this.riskModelUsed = riskModelUsed;
        this.riskStatus = riskStatus;
        this.stopsAhead = stopsAhead;
        this.stopsBehind = stopsBehind;
        this.kittRiskStatus = kittRiskStatus;
        this.startTime = startTime;
        this.departureTime = departureTime;
        this.calculationTime = calculationTime;
        this.initialHour = initialHour;
        this.packageRate = packageRate;
        this.delta = delta;
        this.remainingStops = remainingStops;
        this.targetCompletion = targetCompletion;
        this.timeLeft = timeLeft;
        this.departureEndTime = departureEndTime;
        this.stopsRate = stopsRate;
    }

    public void insert() {
        String statement = "INSERT INTO rawdata (urlKey, url, date, time, hour, minute, countoftotalstopsr, countofcompletedstopsr, routeid, routecode, companyid, counttotal, countdelivered, countattempted, countreturning, countdeliveredandattempted, currentcompletion, transporterid, transportername, id, name, numberofpackages, planneddeparturetime, servicetypename, driverid, countofattemptedpackages, countofcompletedpackages, countofcompletedstops, countofdeliveredpackages, countofmissingpackages, countofreattemptablepackages, countofremainingpackages, countofreturnedpackages, countofreturningpackages, countoftotalpackages, countoftotalstops, countofundeliverablepackages, packagesahead, packagesbehind, riskmodelused, riskstatus, stopsahead, stopsbehind, kittriskstatus, starttime, departuretime, calculationtime, initialhour, packagerate, delta, remainingstops, targetcompletion, timeleft, departureendtime, stopsrate) VALUES ('"+this.urlKey+"', '"+this.url+"', '"+this.date+"', '"+this.time+"', '"+this.hour+"', '"+this.minute+"', '"+this.countOfTotalStopsR+"', '"+this.countOfCompletedStopsR+"', '"+this.routeId+"', '"+this.routeCode+"', '"+this.companyId+"', '"+this.countTotal+"', '"+this.countDelivered+"', '"+this.countAttempted+"', '"+this.countReturning+"', '"+this.countDeliveredAndAttempted+"', '"+this.currentCompletion+"', '"+this.transporterId+"', '"+this.transporterName+"', '"+this.id+"', '"+this.name+"', '"+this.numberOfPackages+"', '"+this.plannedDepartureTime+"', '"+this.serviceTypeName+"', '"+this.driverId+"', '"+this.countOfAttemptedPackages+"', '"+this.countOfCompletedPackages+"', '"+this.countOfCompletedStops+"', '"+this.countOfDeliveredPackages+"', '"+this.countOfMissingPackages+"', '"+this.countOfReattemptablePackages+"', '"+this.countOfRemainingPackages+"', '"+this.countOfReturnedPackages+"', '"+this.countOfReturningPackages+"', '"+this.countOfTotalPackages+"', '"+this.countOfTotalStops+"', '"+this.countOfUndeliverablePackages+"', '"+this.packagesAhead+"', '"+this.packagesBehind+"', '"+this.riskModelUsed+"', '"+this.riskStatus+"', '"+this.stopsAhead+"', '"+this.stopsBehind+"', '"+this.kittRiskStatus+"', '"+this.startTime+"', '"+this.departureTime+"', '"+this.calculationTime+"', '"+this.initialHour+"', '"+this.packageRate+"', '"+this.delta+"', '"+this.remainingStops+"', '"+this.targetCompletion+"', '"+this.timeLeft+"', '"+this.departureEndTime+"', '"+this.stopsRate+"')";
        Database.commitToDB(statement);
    }

    public String getUrlKey() {
        return urlKey;
    }

    public String getUrl() {
        return url;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getHour() {
        return hour;
    }

    public String getMinute() {
        return minute;
    }

    public int getCountOfTotalStopsR() {
        return countOfTotalStopsR;
    }

    public int getCountOfCompletedStopsR() {
        return countOfCompletedStopsR;
    }

    public String getRouteId() {
        return routeId;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public String getCompanyId() {
        return companyId;
    }

    public int getCountTotal() {
        return countTotal;
    }

    public int getCountDelivered() {
        return countDelivered;
    }

    public int getCountAttempted() {
        return countAttempted;
    }

    public int getCountReturning() {
        return countReturning;
    }

    public int getCountDeliveredAndAttempted() {
        return countDeliveredAndAttempted;
    }

    public double getCurrentCompletion() {
        return currentCompletion;
    }

    public String getTransporterId() {
        return transporterId;
    }

    public String getTransporterName() {
        return transporterName;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfPackages() {
        return numberOfPackages;
    }

    public String getPlannedDepartureTime() {
        return plannedDepartureTime;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public String getDriverId() {
        return driverId;
    }

    public int getCountOfAttemptedPackages() {
        return countOfAttemptedPackages;
    }

    public int getCountOfCompletedPackages() {
        return countOfCompletedPackages;
    }

    public int getCountOfCompletedStops() {
        return countOfCompletedStops;
    }

    public int getCountOfDeliveredPackages() {
        return countOfDeliveredPackages;
    }

    public int getCountOfMissingPackages() {
        return countOfMissingPackages;
    }

    public int getCountOfReattemptablePackages() {
        return countOfReattemptablePackages;
    }

    public int getCountOfRemainingPackages() {
        return countOfRemainingPackages;
    }

    public int getCountOfReturnedPackages() {
        return countOfReturnedPackages;
    }

    public int getCountOfReturningPackages() {
        return countOfReturningPackages;
    }

    public int getCountOfTotalPackages() {
        return countOfTotalPackages;
    }

    public int getCountOfTotalStops() {
        return countOfTotalStops;
    }

    public int getCountOfUndeliverablePackages() {
        return countOfUndeliverablePackages;
    }

    public int getPackagesAhead() {
        return packagesAhead;
    }

    public int getPackagesBehind() {
        return packagesBehind;
    }

    public String getRiskModelUsed() {
        return riskModelUsed;
    }

    public String getRiskStatus() {
        return riskStatus;
    }

    public int getStopsAhead() {
        return stopsAhead;
    }

    public int getStopsBehind() {
        return stopsBehind;
    }

    public String getKittRiskStatus() {
        return kittRiskStatus;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getCalculationTime() {
        return calculationTime;
    }

    public double getInitialHour() {
        return initialHour;
    }

    public double getPackageRate() {
        return packageRate;
    }

    public double getDelta() {
        return delta;
    }

    public int getRemainingStops() {
        return remainingStops;
    }

    public double getTargetCompletion() {
        return targetCompletion;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public String getDepartureEndTime() {
        return departureEndTime;
    }

    public double getStopsRate() {
        return stopsRate;
    }
}

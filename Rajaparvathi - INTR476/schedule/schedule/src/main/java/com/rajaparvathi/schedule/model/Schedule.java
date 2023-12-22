package com.rajaparvathi.schedule.model;

import java.time.LocalDateTime;

public class Schedule {
    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private String trainId;
    private String routeId;

    public <Train> void setTrain(Train train) {
        
    }

    public <Route> void setRoute(Route route) {
    }
}

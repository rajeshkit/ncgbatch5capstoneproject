import java.time.LocalDateTime;

public class NewScheduleRequest {
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private Long trainId;
    private Long routeId;


    public NewScheduleRequest() {
    }

    public NewScheduleRequest(LocalDateTime departureDateTime, LocalDateTime arrivalDateTime, Long trainId, Long routeId) {
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.trainId = trainId;
        this.routeId = routeId;
    }

    
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

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }
}

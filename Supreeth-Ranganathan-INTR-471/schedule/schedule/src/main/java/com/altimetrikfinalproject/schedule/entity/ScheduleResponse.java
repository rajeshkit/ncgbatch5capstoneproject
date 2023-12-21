package com.altimetrikfinalproject.schedule.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.util.RouteMatcher;

import java.util.Date;
@Data
@Builder
public class ScheduleResponse {
    private Date departureDateTime;
    private Date arrivalDateTime;
    private int trainId;
    private int routeId;
    private TrainResponse trainResponse;
    private RouteResponse routeResponse;
}

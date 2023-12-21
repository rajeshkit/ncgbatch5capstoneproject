package com.altimetrikfinalproject.schedule.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RouteResponse {
    private int routeId;
    private String source;
    private String destination;
    private int totalKms;
}

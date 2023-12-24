package com.project.Schedule.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Schedule {
    @ManyToOne
    @JoinColumn(name = "trainNumber")
    private TrainResponse train;
    @ManyToOne
    @JoinColumn(name = "routeId")
    private RouteResponse route;

    @Id
    private int scheduleId ;
    private String arrivalDateTime ;
    private String departureDateTime ;


}


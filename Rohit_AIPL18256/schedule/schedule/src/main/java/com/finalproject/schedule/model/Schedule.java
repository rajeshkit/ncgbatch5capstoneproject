package com.finalproject.schedule.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.RouteMatcher;


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

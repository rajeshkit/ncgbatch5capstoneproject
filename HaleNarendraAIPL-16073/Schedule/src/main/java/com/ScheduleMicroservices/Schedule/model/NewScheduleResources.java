package com.ScheduleMicroservices.Schedule.model;

import com.RouteMicroservices.Route.model.RouteResources;
import com.TrainBookingSystem.Train.model.TrainResources;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "New_Schedule_Resources")
public class NewScheduleResources {

    @Id
    private Long scheduleId;

    @NotNull(message = "Enter Departure Date Time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp departureDateTime;

    @NotNull(message = "Enter Arrival Date Time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp arrivalDateTime;

    @ManyToOne
    @JoinColumn(name = "trainNumber")
    private TrainResources trainResources;

    @ManyToOne
    @JoinColumn(name="routeId")
    private RouteResources routeResources;

}



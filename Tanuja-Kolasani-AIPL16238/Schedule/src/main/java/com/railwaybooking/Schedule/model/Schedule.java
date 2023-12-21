package com.railwaybooking.Schedule.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.railwaybooking.Route.model.RouteInfo;
import com.railwaybooking.Train.model.TrainInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", sequenceName = "sequence", initialValue = 1000)
    private Long scheduleId;
    @NotNull(message = "Enter Arrival Date and Time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arrivalDateTime;
    @NotNull(message = "Enter Departure Date and Time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departureDateTime;
    @ManyToOne
    @JoinColumn(name="trainNumber")
    private TrainInfo trainInfo;
    @ManyToOne
    @JoinColumn(name="routeId")
    private RouteInfo routeInfo;





}

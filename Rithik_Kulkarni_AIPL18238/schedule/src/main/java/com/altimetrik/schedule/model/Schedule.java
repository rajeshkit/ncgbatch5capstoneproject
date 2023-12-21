package com.altimetrik.schedule.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {


    private int scheduleId;

    private LocalDateTime departureDateTime;

    private LocalDateTime arrivalDateTime;

    @ManyToOne
    @JoinColumn(name = "trainNumber")
    private Train train;

    @ManyToOne
    @JoinColumn(name = "routeId")
    private Route route;
}

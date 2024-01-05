package com.trainbooking.schedulemicroservices.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {

    private int scheduleId;
    private String departureDateTime;
    private String arrivalDateTime;

    @ManyToOne
    @JoinColumn(name = "trainNumber")
    private Train train;
    private Route route;
}

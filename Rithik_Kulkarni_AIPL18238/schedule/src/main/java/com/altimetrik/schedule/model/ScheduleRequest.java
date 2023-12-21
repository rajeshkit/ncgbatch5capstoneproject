package com.altimetrik.schedule.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ScheduleRequest {

    @Id
    @GeneratedValue
    private int scheduleId;

    @NotNull(message = "Please enter the Departure Date and time")
    private LocalDateTime departureDateTime;

    @NotNull(message = "Please enter the Arrival Date and time")
    private LocalDateTime arrivalDateTime;

    @NotNull(message = "Please enter the Train Number")
    private int trainNumber;

    @NotNull(message = "Please enter the Route Id")
    private int routeId;
}


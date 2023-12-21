package com.altimetrik.trainschedule.modle;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class NewSchedule {
    @Id
    @GeneratedValue
    private int scheduleId;
    private int trainNumber;
    private int routeId;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
}
package com.schedulemicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleRequest {
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private int trainNumber;
    private int routeId;
}

package com.booking_details.schedule.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequest {

    private long scheduleId;
    private LocalDateTime arrivalDateTime;
    private LocalDateTime departureDateTime;
    private long trainNumber;
    private long routeId;
}

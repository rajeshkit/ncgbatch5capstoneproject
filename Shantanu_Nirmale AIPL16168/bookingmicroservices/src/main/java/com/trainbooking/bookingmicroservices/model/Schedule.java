package com.trainbooking.bookingmicroservices.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    private int scheduleId;
    private String departureDateTime;
    private String arrivalDateTime;
    private int trainNumber;
    private int routeId;
}

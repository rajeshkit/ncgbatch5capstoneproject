package com.trainbooking.bookingmicroservices.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    private int bookingId;
    private String bookingBy;
    private String email;
    private Train train;
    private Route route;
    private Schedule schedule;
}

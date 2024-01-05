package com.trainbooking.bookingmicroservices.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Train {
    private int trainNumber;
    private String trainName;
    private int acCoaches;
    private int acCoachTotalSeats;
    private int sleeperCoaches;
    private int sleeperCoachesTotalSeats;
    private int generalCoaches;
    private int generalCoachesTotalSeats;
}

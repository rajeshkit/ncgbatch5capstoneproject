package com.altimetrikfinalproject.schedule.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrainResponse {
    private int trainNumber;
    private String trainName;
    private int totalKms;
    private int acCoaches;
    private int totalAcCoachSeats;
    private int sleeperCoaches;
    private int sleeperCoachesTotalSeats;
    private int generalCoaches;
    private int generalCoachesTotalSeats;
}

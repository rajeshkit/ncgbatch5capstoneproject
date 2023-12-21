package com.altimetrikfinalproject.trainmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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

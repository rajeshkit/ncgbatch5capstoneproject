package com.rajaparvathi.train.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder


public class Train {
    @Id
    @GeneratedValue
    private static int trainNumber;

    private String trainName;
    private double totalKms;
    private int acCoaches;
    private int acCoachesTotalSeats;
    private int sleeperCoaches;
    private int sleeperCoachesTotalSeats;
    private int generalCoaches;
    private int generalCoachesTotalSeats;


    public static int getTrainNumber() {
        return trainNumber;
    }
}

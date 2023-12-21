package com.altimetrik.schedulemicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Train {
    private int trainNumber;
    private String trainName;
    private double totalKms;
    private int acCoaches;
    private int acCoachTotalSeats;
    private int sleeperCoaches;
    private int sleeperCoachTotalSeats;
    private int generalCoaches;
    private int generalCoachTotalSeats;

    @Override
    public String toString() {
        return "Train{" +
                "trainNumber=" + trainNumber +
                ", trainName='" + trainName + '\'' +
                ", totalKms=" + totalKms +
                ", acCoaches=" + acCoaches +
                ", acCoachTotalSeats=" + acCoachTotalSeats +
                ", sleeperCoaches=" + sleeperCoaches +
                ", sleeperCoachTotalSeats=" + sleeperCoachTotalSeats +
                ", generalCoaches=" + generalCoaches +
                ", generalCoachTotalSeats=" + generalCoachTotalSeats +
                '}';
    }
}

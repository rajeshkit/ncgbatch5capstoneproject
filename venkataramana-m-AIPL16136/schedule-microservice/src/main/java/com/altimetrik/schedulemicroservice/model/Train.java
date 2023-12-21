package com.altimetrik.schedulemicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Entity
public class Train {
    //    @Id
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
        return "Train [" +
                "trainNumber=" + trainNumber +
                ", trainName='" + trainName + '\'' +
                ", totalKms=" + totalKms +
                ", acCoaches=" + acCoaches +
                ", acCoachTotalSeats=" + acCoachTotalSeats +
                ", sleeperCoaches=" + sleeperCoaches +
                ", sleeperCoachTotalSeats=" + sleeperCoachTotalSeats +
                ", generalCoaches=" + generalCoaches +
                ", generalCoachTotalSeats=" + generalCoachTotalSeats +
                ']';
    }
}


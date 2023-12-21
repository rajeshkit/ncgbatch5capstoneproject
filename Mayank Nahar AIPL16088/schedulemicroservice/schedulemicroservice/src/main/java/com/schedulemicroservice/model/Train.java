package com.schedulemicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Train {
    @Id
    private int trainNumber;
    private String trainName;
    private double totalKms;
    private int acCoaches;
    private int acCoachesTotalSeats;
    private int sleeperCoaches;
    private int sleeperCoachTotalSeats;
    private int generalCoaches;
    private int generalCoachTotalSeats;
}



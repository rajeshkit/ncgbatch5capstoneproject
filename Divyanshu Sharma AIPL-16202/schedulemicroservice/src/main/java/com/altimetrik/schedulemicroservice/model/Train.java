package com.altimetrik.schedulemicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Train {

    @Id
    private int trainNumber;
    private String trainName;
    private double totalKm;
    private int acCoaches;
    private int acCoachesTotalSeats;
    private int sleeperCoaches;
    private int sleeperCoachesTotalSeats;
    private int generalCoaches;
    private int generalCoachesTotalSeats;
}

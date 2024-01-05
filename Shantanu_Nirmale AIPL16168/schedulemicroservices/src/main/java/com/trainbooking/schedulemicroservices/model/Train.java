package com.trainbooking.schedulemicroservices.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Train {
    @Id
    private int trainNumber;
    private String trainName;
    private int totalKms;
    private int acCoaches;
    private int acCoachTotalSeats;
    private int sleeperCoaches;
    private int sleeperCoachesTotalSeats;
    private int generalCoaches;
    private int generalCoachesTotalSeats;
}

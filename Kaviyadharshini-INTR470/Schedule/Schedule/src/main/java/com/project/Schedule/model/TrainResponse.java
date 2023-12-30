package com.project.Schedule.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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

public class TrainResponse {
    @Id
    @GeneratedValue
    private int trainNumber ;
    private int totalKms ;
    private String trainName ;
    private int acCoaches ;
    private int acCoachesTotalSeats ;
    private int sleeperCoaches ;
    private int sleeperCoachesTotalSeats ;
    private int generalCoaches ;
    private int generalCoachesTotalSeats ;
}


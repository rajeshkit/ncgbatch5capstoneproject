package com.altimetrik.trainmicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int trainId;
//    @NotEmpty(message ="Please enter the train number")
    private long trainNumber;
    @NotEmpty(message ="Please enter the train name")
    private String trainName;
    private float totalKms;
    @PositiveOrZero(message = "Please enter a non-negative number")
    private int acCoaches;
    private int acCoachTotalSeats;
    @PositiveOrZero(message = "Please enter a non-negative number")
    private int sleeperCoaches;
    private int sleeperCoachTotalSeats;
    @PositiveOrZero(message = "Please enter a non-negative number")
    private int generalCoaches;
    private int generalCoachTotalSeats;
}

package com.train.trainmicroservice.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import jakarta.persistence.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder

public class Train {

    @Id

    @NotNull(message = "Train Number Should Not Be Empty")
    private int trainNumber;
    @NotNull(message = "Name Should Not be empty")
    private String trainName;
    @Positive(message = "Enter Km in Positive value")
    private double totalKms;
    @PositiveOrZero(message = "AC coaches should be zero or Positive")
    private int acCoaches;
    @PositiveOrZero(message = "AC coaches seats should be zero or Positive")
    private int acCoachesTotalSeats;
    @PositiveOrZero(message = "Sleeper coaches should be zero or Positive")
    private int sleeperCoaches;
    @PositiveOrZero(message = "Sleeper coaches seats should be zero or Positive")
    private int sleeperCoachTotalSeats;
    @PositiveOrZero(message = "General coaches should be zero or Positive")
    private int generalCoaches;
    @PositiveOrZero(message = "General coaches seats should be zero or Positive")
    private int generalCoachTotalSeats;
}



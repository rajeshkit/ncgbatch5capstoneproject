package com.altimetrik.trainmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class Train {
    @Id
    @GeneratedValue(generator = "trainNumber", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "trainNumber", initialValue = 12345, sequenceName = "trainNumber")
    private int trainNumber;

    @NotEmpty(message = "Please enter the trainName")
    private String trainName;

    @NotNull(message = "Please enter the totalKms")
    @Positive(message = "Enter the positive value for the totalKms")
    private double totalKms;

    @NotNull(message = "Please enter the acCoaches")
    @Positive(message = "Enter the positive value for the acCoaches")
    private int acCoaches;

    @NotNull(message = "Please enter the acCoachTotalSeats")
    @Positive(message = "Enter the positive value for the acCoachTotalSeats")
    private int acCoachTotalSeats;

    @NotNull(message = "Please enter the sleeperCoaches" )
    @Positive(message = "Enter the positive value for the sleeperCoaches")
    private int sleeperCoaches;

    @NotNull(message = "Please enter the sleeperCoachTotalSeats")
    @Positive(message = "Enter the positive value for the sleeperCoachTotalSeats")
    private int sleeperCoachTotalSeats;

    @NotNull(message = "Please enter the generalCoaches" )
    @Positive(message = "Enter the positive value for the generalCoaches")
    private int generalCoaches;

    @NotNull(message = "Please enter the generalCoachTotalSeats" )
    @Positive(message = "Enter the positive value for the generalCoachTotalSeats")
    private int generalCoachTotalSeats;
}

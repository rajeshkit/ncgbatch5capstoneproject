package com.altimetrik.trainmicroservices.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Train {
    @Id

    @GeneratedValue(generator = "trainNumber", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "trainNumber", initialValue = 10000, sequenceName = "trainNumber")
    private long trainNumber;

    @NotEmpty
    @NotNull
    @NotBlank(message = "Train name is required")
    private String trainName;

    @NotNull(message = "Total kilometers must be provided")
    @Positive(message = "Should be Greater than Zero")
    private double totalKms;

    @NotNull(message = "Number of AC coaches must be provided")
    @Positive(message = "Should be Greater than Zero")
    private int acCoaches;

    @NotNull(message = "Total seats in AC coaches must be provided")
    @Positive(message = "Should be Greater than Zero")
    private int acCoachTotalSeats;

    @NotNull(message = "Number of sleeper coaches must be provided")
    @Positive(message = "Should be Greater than Zero")
    private int sleeperCoaches;

    @NotNull(message = "Total seats in sleeper coaches must be provided")
    @Positive(message = "Should be Greater than Zero")
    private int sleeperCoachTotalSeats;

    @NotNull(message = "Number of general coaches must be provided")
    @Positive(message = "Should be Greater than Zero")
    private int generalCoaches;

    @NotNull(message = "Total seats in general coaches must be provided")
    @Positive(message = "Should be Greater than Zero")
    private int generalCoachTotalSeats;
}

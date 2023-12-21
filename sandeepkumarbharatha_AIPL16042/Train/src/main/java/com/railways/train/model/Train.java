package com.railways.train.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @Positive(message = "Enter the train  number greater than zero")
    private long trainNumber;
    @NotEmpty(message = "Enter train name")
    private String trainName;
    @Positive(message = "Enter the train travelled kms up to date greater than zero")
    @NotNull
    private long totalKms;
    @Positive(message = "Enter the no'of Ac coaches greater than zero")
    @NotNull
    private int acCoaches;
    @Positive(message = "enter total no'of seats greater than zero")
    @NotNull
    private long acCoachTotalSeats;
    @Positive(message = "Enter no'of sleeper coaches greater than zero")
    @NotNull
    private int sleeperCoaches;
    @Positive(message = "Enter no'of sleeper coaches seats greater than zero")
    @NotNull
    private long sleeperCoachTotalSeats;
    @Positive(message = "Enter no'of general coaches greater than zero")
    @NotNull
    private int generalCoaches;
    @Positive(message = "Enter no'of general coaches seats greater than zero")
    @NotNull
    private long generalCoacheTotalSeats;

}


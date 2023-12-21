package com.altimetrik.train.model;

//POJO class for train component

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Train {

    @Id
    @GeneratedValue
    private int trainNumber;

    @NotEmpty(message = "Please Enter the Train Name.")
    private String trainName;

    @Positive(message = "Please Enter Positive Number or Zero.")
    private int totalKms;

    @PositiveOrZero(message = "Please Enter Positive Number or Zero.")
    private int acCoaches;

    @PositiveOrZero(message = "Please Enter Positive Number or Zero.")
    private int acCoachTotalSeats;

    @PositiveOrZero(message = "Please Enter Positive Number or Zero.")
    private int sleeperCoaches;

    @PositiveOrZero(message = "Please Enter Positive Number or Zero.")
    private int sleeperCoachTotalSeats;

    @PositiveOrZero(message = "Please Enter Positive Number or Zero.")
    private int generalCoaches;

    @PositiveOrZero(message = "Please Enter Positive Number or Zero.")
    private int generalCoachTotalSeats;

}

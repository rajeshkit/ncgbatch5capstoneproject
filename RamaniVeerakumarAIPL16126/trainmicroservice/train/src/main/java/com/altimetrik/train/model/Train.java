package com.altimetrik.train.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder


public class Train {
    @Id
    @GeneratedValue
    private int trainNumber;
    @NotEmpty(message = "Please enter the Train name correctly")
    private String trainName;
    @NotNull(message = "Please enter the TotalKms correctly")
    private int totalKms;
    private int acCoaches;
    private int acCoachTotalSeats;
    private int sleeperCoaches;
    private int sleeperCoachTotalSeats;
    private int generalCoaches;
    private int generalCoacheTotalSeats;



}

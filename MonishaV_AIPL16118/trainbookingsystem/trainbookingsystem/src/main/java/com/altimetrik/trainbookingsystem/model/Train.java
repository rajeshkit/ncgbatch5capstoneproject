package com.altimetrik.trainbookingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class Train {
    @Id
    private String trainNo;
    @NotNull(message = "Please enter the train number")
    private String trainName;
    @NotNull(message = "Please enter the train name")
    private int totalKms;
    @NotNull(message = "Please enter the total kms")
    private int acCoaches;
    private int acCoachTotalSeats;
    private int sleeperCoaches;
    private int sleeperCoachTotalSeats;
    private int generalCoaches;
    private int generalCoacheTotalSeats;



}

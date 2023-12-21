package com.altimetrik.trainbookingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class Train {
    @Id
    @GeneratedValue
    private int trainNo;

    private String trainName;

    private int totalKms;

    private int acCoaches;

    private int acCoachTotalSeats;

    private int sleeperCoaches;

    private int sleeperCoachTotalSeats;

    private int generalCoaches;

    private int generalCoachTotalSeats;



}

package com.railways.train.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="train_resources")
public class Train {
    @Id
    @Positive(message = "Enter Train Number")
    private long trainNumber;
    @NotEmpty(message = "Enter Train Name")
    private String trainName;
    @Positive(message = "Enter Total Kilometers")
    private double totalKms;
    @Positive(message = "Enter Total No.Of Ac Coaches")
    private int acCoaches;
    @Positive(message = "Enter Total No.of Seats in Ac Coaches")
    private int acCoachTotalSeats;
    @Positive(message = "Enter Total No.of Sleeper Coaches")
    private int sleeperCoaches;
    @Positive(message = "Enter Total No.Of Seats in Sleeper Coaches")
    private int sleeperCoachTotalSeats;
    @Positive(message = "Enter Total No.Of General Coaches")
    private int generalCoaches;
    @Positive(message = "Enter Total No.Of Seats in General Coaches")
    private int generalCoachTotalSeats;
}

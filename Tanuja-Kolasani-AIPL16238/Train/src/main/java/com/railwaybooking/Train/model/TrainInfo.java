package com.railwaybooking.Train.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainInfo {
    @Id
    @Positive(message = "Enter Positive Train Number")
    private long trainNumber;
    @NotEmpty(message = "Enter Train Name")
    private String trainName;
    @Positive(message = "Enter Positive Total Kilometers")
    private double totalKms;
    @Positive(message = "Enter Valid Total Number of Ac Coaches")
    private int acCoaches;
    @Positive(message = "Enter Valid Total Number of seats in Ac Coaches")
    private int acCoachTotalSeats;
    @Positive(message = "Enter Valid Total Number of Sleeper Coaches")
    private int sleeperCoaches;
    @Positive(message = "Enter Valid Total Number of seats in Sleeper Coaches")
    private int sleeperCoachTotalSeats;
    @Positive(message = "Enter Valid Total Number of General Coaches")
    private int generalCoaches;
    @Positive(message = "Enter Valid Total Number of seats in General Coaches")
    private int generalCoachTotalSeats;

}

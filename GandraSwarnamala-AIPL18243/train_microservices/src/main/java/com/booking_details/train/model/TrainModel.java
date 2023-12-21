package com.booking_details.train.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "train_details")
public class TrainModel {
    @Id
    private long trainNumber;
    @NotEmpty(message = "Please Enter Train Name: ")
    private String trainName;
    @NotNull(message = "Please Enter total Kilometer: ")
    private Double totalKms;
    private int acCoaches;
    private int acCoachTotalSeats;
    private int sleeperCoaches;
    private int sleeperCoachTotalSeats;
    private int generalCoaches;
    private int generalCoachesTotalSeats;
}

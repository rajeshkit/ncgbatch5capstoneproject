package com.altimetrik.trainbooking.modle;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Train {

    @Id
    @GeneratedValue
    private int trainNumber;
    @NotEmpty (message = "Please enter the product name")
    private String trainName;
    @Positive(message = "Please enter the AC coaches")
    private int acCoaches;
    @Positive
    private int totalKms;
    @NotNull
    private int acCoachTotalSeats;
    @NotNull
    private int sleeperCoaches;
    @NotNull
    private int sleeperCoachTotalSeats;
    @NotNull
    private int generalCoaches;
    @NotNull
    private int generalCoacheTotalSeats;

}
package com.train.trainmicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
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
    @Positive(message = "Train number should be positive")
    private int trainNumber;
    @NotBlank(message = "Train name should not be empty")
    @NotNull(message = "Train name should not be null")
    private String trainName;
    @Positive(message = "Total Kms should be positive")
    private double totalKms;
    @Positive(message = "acCoaches should not be negative")
    private int acCoaches;
    @Positive(message = "acCoacheTotalSeats should not be negative")
    private int acCoachTotalSeats;
    @Positive(message = "sleeperCoaches should not be negative")
    private int sleeperCoaches;
    @Positive(message = "sleeperCoachTotalSeats should not be negative")
    private  int sleeperCoachTotalSeats;
    @Positive(message = "generalCoaches should not be negative")
    private int generalCoaches;
    @Positive(message = "generalCoachesTotalSeats should not be negative")
    private int generalCoachesTotalSeats;
}

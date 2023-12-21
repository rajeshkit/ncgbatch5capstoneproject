package com.booking.train.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "train_resources")
@Builder
public class TrainResources {
    @Id
    @Positive(message = "Train number should be positive")
    private Long trainNumber;
    @NotEmpty(message = "Train Name should not be Empty")
    private String trainName;
    @Positive(message = "Total Kms should be positive")
    private Double totalKms;
    @Positive(message = "AC-Coaches should not be negative")
    private int acCoaches;
    @Positive(message = "AC-Coaches Total Seats should not be negative")
    private int acCoachTotalSeats;
    @Positive(message = "Sleeper-Coaches should not be negative")
    private int sleeperCoaches;
    @Positive(message = "Sleeper-Coaches Total Seats should not be negative")
    private int sleeperCoachTotalSeats;
    @Positive(message = "General-Coaches should not be negative")
    private int generalCoaches;
    @Positive(message = "General-Coaches Total Seats should not be negative")
    private int generalCoachesTotalSeats;
}

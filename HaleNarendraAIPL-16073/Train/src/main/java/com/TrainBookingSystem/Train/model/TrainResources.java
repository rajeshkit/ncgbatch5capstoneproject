package com.TrainBookingSystem.Train.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Train_Resources")
public class TrainResources {
    @Id
    private Long trainNumber;
    @NotEmpty(message = "Enter Train name")
    private String trainName;
    @NotNull(message = "Enter total kilometer")
    @Positive(message = "Value must be positive")
    private Double totalKms;
    @NotNull(message = "Enter total Ac-Coaches")
    @Positive(message = "Value must be positive")
    private int acCoaches;
    @NotNull(message = "Enter total seats for Ac-Coaches")
    @Positive(message = "Value must be positive")
    private int acCoachTotalSeats;
    @NotNull(message = "Enter total Sleeper-Coaches")
    @Positive(message = "Value must be positive")
    private int sleeperCoaches;
    @NotNull(message = "Enter total seats for Sleeper-Coaches")
    @Positive(message = "Value must be positive")
    private int sleeperCoachTotalSeats;
    @NotNull(message = "Enter total General-Coaches")
    @Positive(message = "Value must be positive")
    private int generalCoaches;
    @NotNull(message = "Enter total seats for General-Coaches")
    @Positive(message = "Value must be positive")
    private int generalCoachesTotalSeats;



}

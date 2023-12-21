package com.trainmicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int trainNumber;
    @NotBlank(message = "Please enter the Train Name.")
    private String trainName;
    @Positive(message = "Total kilometers must be a positive number.")
    private int totalKms;
    @PositiveOrZero(message = "Number of AC coaches must be greater or equal to 0.")
    private int acCoaches;
    @PositiveOrZero(message = "Number of AC coach seats must be greater or equal to 0.")
    private int acCoachTotalSeats;
    @PositiveOrZero(message = "Number of sleeper coaches must be greater or equal to 0.")
    private int sleeperCoaches;
    @PositiveOrZero(message = "Number of sleeper coach seats must be greater or equal to 0.")
    private int sleeperCoachTotalSeats;
    @Positive(message = "Number of general coaches must be greater then 0.")
    private int generalCoaches;
    @Positive(message = "Number of general coach seats must be greater then 0.")
    private int generalCoachTotalSeats;
}

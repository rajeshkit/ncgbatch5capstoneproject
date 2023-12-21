package com.altimetrik.trainmicroservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Train
{
        @Id
        @GeneratedValue
        @Positive(message = "Train number must be a positive value")
        private int trainNumber;
        @NotNull(message = "Train name cannot be null")
        @NotEmpty(message = "Train name cannot be null or empty")
        private String trainName;

        @Positive(message = "Total Kms must be a positive value")
        private double totalKms;

        @Min(value = 0, message = "Number of AC coaches must not be negative")
        private int acCoaches;

        @Min(value = 0, message = "Number of AC coach total seats must not be negative")
        private int acCoachTotalSeats;

        @Min(value = 0, message = "Number of sleeper coaches must not be negative")
        private int sleeperCoaches;

        @Min(value = 0, message = "Number of sleeper coach total seats must not be negative")
        private int sleeperCoachTotalSeats;

        @Min(value = 0, message = "Number of general coaches must not be negative")
        private int generalCoaches;

        @Min(value = 0, message = "Number of general coach total seats must not be negative")
        private int generalCoachTotalSeats;
    }



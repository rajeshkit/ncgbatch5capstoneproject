package com.finalproject.railwaysmicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor

@Data
@Entity
@Builder

public class Railway {


    @Id
    @GeneratedValue

    private  int trainNumber;

    @NotEmpty(message = "Train Name field cannot be empty")
    private String trainName ;

    @NotNull(message = "TotalKms field cannot be empty")
    private int totalKms;

    @NotNull(message = "AC coaches value cannot be empty here")
    private int acCoaches ;

    @NotNull(message = "Total seats field of AC coaches cannot be empty")
    private int acCoachesTotalSeats ;

    @NotNull(message = "Sleeper coaches field cannot be empty")
    private int sleeperCoaches ;

    @NotNull(message = "Total seats field of Sleeper Coaches cannot be empty")
    private int sleeperCoachesTotalSeats ;

    @NotNull(message = "General coaches field cannot be empty ")
    private int generalCoaches ;

    @NotNull(message = "Total seats field of General coaches cannot be empty ")
    private int generalCoachesTotalSeats ;

}

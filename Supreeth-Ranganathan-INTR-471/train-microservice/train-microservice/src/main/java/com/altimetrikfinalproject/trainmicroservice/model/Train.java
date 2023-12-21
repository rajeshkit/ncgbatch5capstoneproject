package com.altimetrikfinalproject.trainmicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Train {
    @Id
    @GeneratedValue
    private int trainNumber;
    @NotEmpty(message = "Train Name cannot be Empty")
    private String trainName;
    private int totalKms;
    @NotNull(message = "AC Coaches field cannot be Empty")
    private int acCoaches;
    @NotNull(message = "AC Coaches Total Seats field cannot be Empty")
    private int totalAcCoachSeats;
    @NotNull(message = "Sleeper Coaches field cannot be Empty")
    private int sleeperCoaches;
    @NotNull(message = "Sleeper Coaches Total Seat field cannot be Empty")
    private int sleeperCoachesTotalSeats;
    @NotNull(message = "General Coaches field cannot be Empty")
    private int generalCoaches;
    @NotNull(message = "General Coaches Total Seats field cannot be Empty")
    private int generalCoachesTotalSeats;
}

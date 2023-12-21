package com.altimetrik.trainbooking.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Train {
    @Id
    @GeneratedValue
    private int trainNumber;
    @NotNull(message = "Please enter the train number")
    private String trainName;
    @NotNull(message = "Please enter the train name")
    private int totalKilometers;
    @NotNull(message = "Please enter the total kilometers")
    private int acCoaches;
    @NotNull(message = "Please enter the no of ac coaches")
    private int totalAcCoachSeats;
    @NotNull(message = "Please enter the no of ac coach total seats")
    private int sleeperCoaches;
    @NotNull(message = "Please enter the sleeper coaches")
    private int totalSleeperCoachSeats;
    @NotNull(message = "Please enter the sleeper coach total seats")
    private int generalCoaches;
    @NotNull(message = "Please enter the general coaches")
    private int totalGeneralCoachSeats;

}


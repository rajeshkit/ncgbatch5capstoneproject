package com.altimetrik.train.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "train")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Valid
public class Train {
    @Id
    @Positive(message = "Enter the Train Number")
    private int trainNo;
    @NotEmpty(message = "Enter the Train Name")
    private String trainName;
    @Positive(message = "Enter the Total travel Distance in Kilometers")
    private int totalKms;
    @PositiveOrZero(message = "Enter the Total Number of AC Coaches")
    private int acCoaches;
    @PositiveOrZero(message = "Enter the Total Number of AC Seats")
    private int acTotalSeats;
    @PositiveOrZero(message = "Enter the Total Number of Sleeper Coaches")
    private int sleeperCoaches;
    @PositiveOrZero(message = "Enter the Total Number of Sleeper Seats")
    private int sleeperTotalSeats;
    @PositiveOrZero(message = "Enter the Total Number of General Coaches")
    private int generalCoaches;
    @PositiveOrZero(message = "Enter the Total Number of General Seats")
    private int generalTotalSeats;

}

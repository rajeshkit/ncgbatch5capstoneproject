package com.Booking.train.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="Train_Table")
public class TrainResources {
    @Id
    @Positive
    private Long trainNumber;
    @NotEmpty(message="enter train name: ")
    private String trainName;
    @NotNull(message="enter total kilometers: ")
    @Positive
    private Double totalkms;
    @Positive
    private int acCoaches;
    @Positive
    private int acCoachTotalSeats;
    @Positive
    private int sleepercoaching;
    @Positive
    private int sleeperCoachTotalSeats;
    @Positive
    private int generalCoaches;
    @Positive
    private int generalCoachesTotalSeats;


}

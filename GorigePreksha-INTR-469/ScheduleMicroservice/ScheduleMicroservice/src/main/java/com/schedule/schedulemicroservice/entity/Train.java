package com.schedule.schedulemicroservice.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long train_id;
    @NotNull
    private int trainNumber;

    @NotEmpty
    private String trainName;

    @NotNull
    private double totalKms;

    @NotNull
    private int acCoaches;

    @NotNull
    private int acCoachTotalSeats;

    @NotNull
    private int sleeperCoaches;

    @NotNull
    private int sleeperCoachTotalSeats;

    @NotNull
    private int generalCoaches;

    @NotNull
    private int generalCoachTotalSeats;


}

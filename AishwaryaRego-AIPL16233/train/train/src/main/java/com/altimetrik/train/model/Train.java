package com.altimetrik.train.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Train {
    @Id
    @GeneratedValue(generator = "trainNum", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "trainNum", initialValue = 16590, sequenceName = "trainNum")
    private int trainNum;

    @NotBlank(message = "Please enter the train name")
    private String trainName;

    @NotNull
    @Min(value = 50,message = "The minimum kms should be 50")
    @Positive(message = "Enter a positive value for the total kms")
    private int totalKms;

    @Min(value = 2,message = "The minimum AC coaches should be 2")
    @Positive(message = "Enter a positive value for the AC coaches")
    private int acCoaches;

    @Positive(message = "Enter a positive value for the Ac coach seats")
    @Min(value = 160,message = "The minimum AC coach seats should be 160")
    private int acCoachTotalSeats;

    @Positive(message = "Enter a positive value for the Sleeper coach")
    @Min(value = 8,message = "The minimum Sleeper coaches should be 8")
    private int sleeperCoach;

    @Positive(message = "Enter a positive value for the Sleeper coach seats")
    @Min(value = 800,message = "The minimum Sleeper coach seats should be 800")
    private int sleeperCoachTotalSeats;

    @Positive(message = "Enter a positive value for the General coach")
    @Min(value = 2,message = "The minimum General coaches should be 2")
    private int generalCoaches;

    @Positive(message = "Enter a positive value for the General coach seats")
    @Min(value = 160,message = "The minimum general coach seats should be 160")
    private int generalCoachTotalSeats;
}

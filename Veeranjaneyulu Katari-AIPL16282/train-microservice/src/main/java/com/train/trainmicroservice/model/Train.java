package com.train.trainmicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder()
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Train {
    @Id
    @Positive
    private int trainNumber;
    private String trainName;
    @Positive
    @NotNull
    private double totalKms;
    @Positive
    @NotNull
    @Min(2)
    private int acCoaches;
    @Positive
    @NotNull
    private int acCoachTotalSeats;
    @Positive
    @NotNull
    private int sleeperCoaches;
    @Positive
    @NotNull
    private  int sleeperCoachTotalSeats;
    @Positive
    @NotNull
    private int generalCoaches;
    @Positive
    @NotNull
    private int generalCoachesTotalSeats;


}

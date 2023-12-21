package com.altimetrik.schedule.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Train {

    @Id
    private int trainNumber;

    private String trainName;

    private int totalKms;

    private int acCoaches;

    private int acCoachTotalSeats;

    private int sleeperCoaches;

    private int sleeperCoachTotalSeats;

    private int generalCoaches;

    private int generalCoachTotalSeats;

}

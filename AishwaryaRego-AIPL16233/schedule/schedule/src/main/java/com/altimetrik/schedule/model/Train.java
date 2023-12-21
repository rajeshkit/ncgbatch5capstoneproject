package com.altimetrik.schedule.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Train {
    private int trainNum;
    private String trainName;
    private int totalKms;
    private int acCoaches;
    private int acCoachTotalSeats;
    private int sleeperCoach;
    private int sleeperCoachTotalSeats;
    private int generalCoaches;
    private int generalCoachTotalSeats;
}

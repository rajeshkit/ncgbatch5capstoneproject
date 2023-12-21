package com.altimetrik.schedule.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Train {
    private int trainNumber;
    private String trainName;
    private List<TrainCoachResponse> trainCoaches;
}

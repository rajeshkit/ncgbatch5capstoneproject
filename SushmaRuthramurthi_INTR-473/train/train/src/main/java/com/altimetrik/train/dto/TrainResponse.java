package com.altimetrik.train.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainResponse {
    private int trainNumber;
    private String trainName;
    private List<TrainCoachResponse> trainCoaches;
}

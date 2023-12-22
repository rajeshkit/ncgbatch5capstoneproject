package com.altimetrik.schedule.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainCoachResponse {
    private int id;
    //private int trainnumber;
    private int coachtypeid;
    private int coachsize;
}

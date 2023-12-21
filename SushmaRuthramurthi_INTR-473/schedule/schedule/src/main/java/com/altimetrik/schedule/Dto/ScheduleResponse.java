package com.altimetrik.schedule.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleResponse{
    private int scheduleId;
    private Date departureDateTime;
    private Date arrivalDateTime;
    private Train train;
    private Route route;
}

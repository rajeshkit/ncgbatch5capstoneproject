package com.altimetrik.schedule.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ScheduleRequest {
    private Date departureDateTime;
    private Date arrivalDateTime;
    private int trainId;
    private int routeId;
}

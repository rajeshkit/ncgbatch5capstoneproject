package com.schedule.schedulemicroservice.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleRequest {
    private Date departureDateTime;
    private Date arrivalDateTime;
    private int trainNumber;
    private int routeId;
}

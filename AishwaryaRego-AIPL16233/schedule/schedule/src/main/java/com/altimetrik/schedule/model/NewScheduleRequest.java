package com.altimetrik.schedule.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewScheduleRequest {
    private String depDateTime;
    private String arrDateTime;
    private int trainNum;
    private int routeId;
}

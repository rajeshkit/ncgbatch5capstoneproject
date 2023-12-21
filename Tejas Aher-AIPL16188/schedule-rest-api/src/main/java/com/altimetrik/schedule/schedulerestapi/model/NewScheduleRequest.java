package com.altimetrik.schedule.schedulerestapi.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewScheduleRequest {

    private Date departureDateTime;
    private Date arrivalDateTime;
    @NotEmpty(message = "Please Enter the trainId")
    private String trainId;
    @NotEmpty(message = "Please Enter the routeId")
    private String routeId;
}

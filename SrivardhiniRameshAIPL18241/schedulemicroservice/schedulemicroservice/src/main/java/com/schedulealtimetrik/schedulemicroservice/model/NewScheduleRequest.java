package com.schedulealtimetrik.schedulemicroservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewScheduleRequest {
//    @NotEmpty(message = "Please enter the departure date and time")
    private String departureDateTime;

//    @NotEmpty(message = "Please enter the arrival date and time")
    private String arrivalDateTime;

//    @Positive(message = "Please enter a positive train number")
    private int trainNumber;

//    @Positive(message = "Please enter a positive train number")
    private int routeId;
}

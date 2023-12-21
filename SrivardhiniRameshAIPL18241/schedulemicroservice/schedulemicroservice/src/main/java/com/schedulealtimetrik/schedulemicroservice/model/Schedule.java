package com.schedulealtimetrik.schedulemicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Schedule extends NewScheduleRequest {
    @Id
    @GeneratedValue
    private int scheduleId;

//    @NotEmpty(message = "Please enter the departure date and time")
    private String departureDateTime;

//    @NotEmpty(message = "Please enter the arrival date and time")
    private String arrivalDateTime;

//    @Positive(message = "Please enter a positive train number")
    private String train;

//    @Positive(message = "Please enter a positive train number")
    private String route;

}

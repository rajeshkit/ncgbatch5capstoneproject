package com.altimetrik.schedulemicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewScheduleRequest {
    @NotNull(message = "Departure date and time must be provided")
    @NotBlank(message = "Departure date and time must not be blank")
    private String departureDateTime;

    @NotNull(message = "Arrival date and time must be provided")
    @NotBlank(message = "Arrival date and time must not be blank")
    private String arrivalDateTime;

    @NotNull(message = "Train ID must be provided")
    @Min(value = 1, message = "Train ID must be a positive integer")
    private int trainId;

    @NotNull(message = "Route ID must be provided")
    @Min(value = 1, message = "Route ID must be a positive integer")
    private int routeId;
}

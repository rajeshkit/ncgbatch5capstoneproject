package com.altimetrik.schedulemicroservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class NewScheduleRequest {

    @NotNull(message = "Enter the Departure Date and time")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    @Future(message = "Departure Date and time must be in the future")
    private Date departureDateTime;

    @NotNull(message = "Enter the Arrival Date and time must")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    @Future(message = "Arrival Date and time must be in the future")
    private Date arrivalDateTime;

    @NotNull(message = "Enter train number")
    @Min(value = 12345, message = "Train number must be at least 12345")
    @Max(value = 20000, message = "Train number must be at most 20000")
    private int trainNumber;

    @NotNull(message = "Enter route ID")
    @Min(value = 23456, message = "Route ID must be at least 23456")
    @Max(value = 30000, message = "Route ID must be at most 30000")
    private int routeId;
}

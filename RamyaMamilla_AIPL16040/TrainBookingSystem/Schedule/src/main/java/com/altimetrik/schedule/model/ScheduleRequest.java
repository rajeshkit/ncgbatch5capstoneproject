package com.altimetrik.schedule.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Valid
public class ScheduleRequest {
    @Positive(message = "Enter the Train Number")
    private int trainNumber;
    @Positive(message = "Enter the Route ID")
    private int routeID;
    @NotNull(message = "Enter the Departure Date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departureDateTime;
    @NotNull(message = "Enter the Arrival Date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arrivalDateTime;
}

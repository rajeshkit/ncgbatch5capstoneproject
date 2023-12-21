package com.railwaybooking.Schedule.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewScheduleInfo {

    @NotNull(message = "Enter Arrival Date and Time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arrivalDateTime;
    @NotNull(message = "Enter Departure Date and Time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departureDateTime;
    @Positive(message = "Enter Train Number")
    private long trainNumber;
    @Positive(message = "Enter Route Id")
    private long routeId;
}

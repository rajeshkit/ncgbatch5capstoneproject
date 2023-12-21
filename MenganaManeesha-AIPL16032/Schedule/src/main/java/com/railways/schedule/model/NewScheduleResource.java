package com.railways.schedule.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
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
public class NewScheduleResource {
    @FutureOrPresent(message = "Enter Future date for Arrival Date and Time")
    @NotNull(message = "Enter Arrival date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arrivalDateTime;
    @FutureOrPresent(message = "Enter Future date for Departure Date and Time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departureDateTime;
    @Positive(message = "Enter Train Number")
    private long trainNumber;
    @Positive(message = "Enter Route Id")
    private long routeId;
}

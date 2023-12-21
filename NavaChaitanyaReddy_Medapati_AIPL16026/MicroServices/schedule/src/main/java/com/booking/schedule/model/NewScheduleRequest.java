package com.booking.schedule.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewScheduleRequest {

    @FutureOrPresent(message = "The departure date and time should not be past")
    @NotNull(message = "Departure Date Time (yyyy-MM-dd HH:mm:ss) should not be null")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp departureDateTime;

    @FutureOrPresent(message = "The arrival date and time should not be past or present")
    @NotNull(message = "Please Enter Arrival Date Time: yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp arrivalDateTime;

    @NotNull(message = "Train Number Should not be null")
    @Positive(message = "Train Number should not be negative")
    private Long trainNumber;

    @NotNull(message = "Route Id Should not be null")
    @Positive(message = "Route Id should not be null")
    private Long routeId;

}

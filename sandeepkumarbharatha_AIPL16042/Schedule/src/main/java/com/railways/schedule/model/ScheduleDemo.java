package com.railways.schedule.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleDemo {
    @Positive(message = "enter positive value")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long scheduleId1;
    @Positive(message = "Enter train number")
    private Long trainNumber;
    @Positive(message = "Enter routeID")
    private Long routeId;
    @Future(message = "enter departure date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp departureDateTime;
    @Future(message = "Enter arrival date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp arrivalDateTime;

}

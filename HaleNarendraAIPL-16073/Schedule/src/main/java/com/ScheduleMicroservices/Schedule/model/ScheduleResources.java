package com.ScheduleMicroservices.Schedule.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "Schedule_Resources")
public class ScheduleResources {

    @Id
    private Long scheduleId;

    @NotNull(message = "Enter Departure Date Time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp departureDateTime;

    @NotNull(message = "Enter Arrival Date Time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp arrivalDateTime;

    @NotNull(message = "Enter Train number")
    private Long trainId;

    @NotNull(message = "Enter Route Id")
    private Long routeId;

}



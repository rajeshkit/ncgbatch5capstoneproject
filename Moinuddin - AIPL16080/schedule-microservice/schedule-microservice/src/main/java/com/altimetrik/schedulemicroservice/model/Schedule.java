package com.altimetrik.schedulemicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "schedules_information")
public class Schedule {
    @Id
    @NotNull(message = "Please Enter Valid Schedule ID")
    @Column(name = "schedule_id")
    private String scheduleId;

    @NotNull(message = "Departure DateTime cannot be null")
    private LocalDateTime departureDateTime;

    @NotNull(message = "Arrival DateTime cannot be null")
    private LocalDateTime arrivalDateTime;

    @NotNull(message = "Route ID cannot be null")
    private String routeId;

    @NotNull(message = "Train ID cannot be null")
    private String trainNumber;
}

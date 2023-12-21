package com.altimetrik.trainschedule.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class Schedule {
    @Id
    @GeneratedValue
    private int scheduleId;
    private Date departureDateTime;
    private Date arrivalDateTime;
    @NotNull(message = "Please enter the trainNumber")
    private String train;
    @NotNull(message = "Please enter the routeId")
    private String route;
}

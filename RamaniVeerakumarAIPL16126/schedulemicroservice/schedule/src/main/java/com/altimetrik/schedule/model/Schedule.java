package com.altimetrik.schedule.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "Please enter the departureDateTime")
    @FutureOrPresent(message = "Please enter the future date for departureDateTime")
    private Date departureDateTime;
    @NotNull(message = "Please enter the arrivalDateTime")
    @FutureOrPresent(message = "Please enter the future date for arrivalDateTime")
    private Date arrivalDateTime;
    private String train;
    private String route;

}

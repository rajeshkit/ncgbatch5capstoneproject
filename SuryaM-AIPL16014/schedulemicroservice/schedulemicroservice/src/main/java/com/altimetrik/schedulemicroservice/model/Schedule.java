package com.altimetrik.schedulemicroservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Schedule {

    @Id
    @GeneratedValue(generator = "scheduleId", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "scheduleId", initialValue = 34567, sequenceName = "scheduleId")
    private int scheduleId;

    @NotNull(message = "Enter the Departure Date and time")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    @Future(message = "Departure Date and time must be in the future")
    private Date departureDateTime;

    @NotNull(message = "Enter the Arrival Date and time")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    @Future(message = "Arrival Date and time must be in the future")
    private Date arrivalDateTime;

    @NotNull(message = "Enter the train details")
    private String train;

    @NotNull(message = "Enter the route details")
    private String route;
}


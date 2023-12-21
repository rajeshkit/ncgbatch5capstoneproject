package com.altimetrik.schedulemicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scheduleId;


    @NotBlank(message = "Departure date and time must not be blank")
    private String departureDateTime;

    @NotBlank(message = "Arrival date and time must not be blank")
    private String arrivalDateTime;

    @NotBlank(message = "Train information must not be blank")
    private String train;

    @NotBlank(message = "Route information must not be blank")
    private String route;
}

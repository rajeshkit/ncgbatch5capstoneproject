package com.altimetrik.routemicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class Route {
    @Id
    @GeneratedValue(generator = "routeId", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "routeId", initialValue = 23456, sequenceName = "routeId")
    private int routeId;

    @NotEmpty(message = "Please enter the Source")
    private String Source;

    @NotEmpty(message = "Please enter the Destination")
    private String Destination;

    @NotNull(message = "Please enter the totalKms")
    @Positive(message = "Enter the positive value for the totalKms")
    private double totalKms;
}

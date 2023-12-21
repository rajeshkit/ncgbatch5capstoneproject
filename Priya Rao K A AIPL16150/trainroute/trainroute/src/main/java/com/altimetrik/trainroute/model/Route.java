package com.altimetrik.trainroute.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class Route {
    @Id
    @GeneratedValue
    private int routeId;
    @NotNull(message = "Please enter the route id")
    private String Source;
    @NotNull(message = "Please enter the Source")
    private String Destination;
    @NotNull(message = "Please enter the Destination")
    private int totalKilometers;

}
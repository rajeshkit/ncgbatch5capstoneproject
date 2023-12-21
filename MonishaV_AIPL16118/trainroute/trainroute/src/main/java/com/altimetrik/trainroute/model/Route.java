package com.altimetrik.trainroute.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class Route {
    @Id
    private String routeId;
    @NotNull(message = "Please enter the route id")
    private String Source;
    @NotNull(message = "Please enter the source")
    private String Destination;
    private int totalkms;

}

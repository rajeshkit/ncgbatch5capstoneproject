package com.altimetrik.routemicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Route {
    @Id
    @GeneratedValue
   //@Positive(message = "Route Id must be a positive value")
    private int routeId;
    @NotNull(message = "Source cannot be null")
    @NotEmpty(message = "Source cannot be null or empty")
    private String source;
    @NotNull(message = "destination cannot be null")
    @NotEmpty(message = "destination cannot be null or empty")
    private String destination;
    @Positive(message = "Route Id must be a positive value")
    private double totalKms;
}

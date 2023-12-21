package com.altimetrik.route.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "route")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Valid
public class Route {
    @Id
    @Positive(message = "Enter the Route ID")
    private int routeId;
    @Positive(message = "Enter the Train Number")
    private int trainNo;
    @NotEmpty(message = "Enter the Source Name")
    private String source;
    @NotEmpty(message = "Enter the Destination Name")
    private String destination;
    @Positive(message = "Enter the Total travel Distance in Kilometers")
    private int totalKm;
}

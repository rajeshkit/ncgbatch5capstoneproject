package com.route.routemicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Route {
    @Id
    private int routeId;
    @NotNull(message = "source name should not be empty")
    private String source;
    @NotNull(message = "Destination name should not be empty")
    private String destination;
    @NotNull
    @Positive(message="Kilometer should not be negative")
    private double totalKms;

}
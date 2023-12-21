package com.railwaybooking.Route.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteInfo {
    @Id
    @Positive(message = "Enter Positive route Id")
    private long routeId;
    @NotEmpty(message = "Enter source")
    private String source;
    @NotEmpty(message = "Enter  Destination")
    private String destination;
    @Positive(message = "Enter Positive Total Kms")
    private double totalKms;

}

package com.booking.route.model;

import jakarta.persistence.*;
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
public class RouteResources {
    @Id
    @Positive(message = "Route-Id should not be negative")
    private Long routeId;
    @NotNull(message = "source should not be null")
    private String source;
    @NotNull(message = "destination should not be null")
    private String destination;
    @Positive(message = "total kms should not be negative")
    private Double totalKms;

}

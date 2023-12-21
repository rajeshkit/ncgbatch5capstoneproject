package com.RouteMicroservices.Route.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Route_Resources")
public class RouteResources {
    @Id
    @Column(name = "route_id")
    private Long routeId;
    @NotEmpty(message = "Enter Source name")
    private String source;
    @NotEmpty(message = "Enter destination name")
    private String destination;
    @Positive(message = "Value must be positive")
    @NotNull(message="Enter total kilometer")
    private Double totalKms;

}

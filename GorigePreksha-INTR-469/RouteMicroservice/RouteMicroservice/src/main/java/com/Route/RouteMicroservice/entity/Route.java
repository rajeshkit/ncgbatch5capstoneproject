package com.Route.RouteMicroservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_number")
    private Long routeNumber;

    @Column(name = "route_id")
    @NotNull
    private int routeId;

    @Column(name = "source", nullable = false)
    @NotEmpty
    private String source;

    @Column(name = "destination", nullable = false)
    @NotEmpty
    private String destination;

    @Column(name = "total_kms", nullable = false)
    @NotNull
    private double totalKms;

}


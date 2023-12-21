package com.altimetrik.routemicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "routes_information")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Route {

    @Id
    @NotEmpty(message = "Please enter a valid Route ID")
    @Column(name = "route_id")
    private String routeId;

    @NotEmpty(message = "Source cannot be empty")
    private String source;

    @NotEmpty(message = "Destination cannot be empty")
    private String destination;

    @Positive(message = "Total kilometers must be Greater then Zero")
    private int totalKms;
}

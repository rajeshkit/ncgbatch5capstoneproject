package com.booking_details.route.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "route_details")
public class RouteModel {
    @Id
    private long routeId;
    @NotEmpty(message = "Please Enter Source Name: ")
    private String source;
    @NotEmpty(message = "Please Enter Destination Name: ")
    private String destination;
    @NotNull(message = "Please Enter Total Kilometers: ")
    private Double totalKms;

}

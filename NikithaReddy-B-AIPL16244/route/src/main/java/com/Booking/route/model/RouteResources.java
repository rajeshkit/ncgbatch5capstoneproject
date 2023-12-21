package com.Booking.route.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name="Route_Table")
@Builder
public class RouteResources {
    @Id
    @Positive
    private Long routeId;
    @NotEmpty(message = "enter source: ")
    private String source;
    @NotEmpty(message = "enter destination: ")
    private String destination;
    @NotNull(message="enter total km: ")
    @Positive
    private Double totalkms;
}

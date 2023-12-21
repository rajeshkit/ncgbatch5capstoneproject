package com.altimetrik.routemicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Route {
    @Id
    @GeneratedValue
    private int routeId;
    @NotEmpty(message="Please enter the source")
    private String source;
    @NotEmpty(message="Please enter the destination")
    private String destination;
    @PositiveOrZero(message="Please enter a non-negative number")
    private float totalKms;
}

package com.route.routemicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Route {
    @Id
    @Positive(message = "routeId should not ne negative")
    private int routeId;
    @NotNull(message = "source should not be null")
    @NotBlank(message = "routeId should not be blank")
    private String source;
    @NotNull(message = "destination should not be null")
    @NotBlank(message = "destination should not be blank")
    private String destination;
    @Positive(message = "total kms should not be negative")
    private double totalKms;
}

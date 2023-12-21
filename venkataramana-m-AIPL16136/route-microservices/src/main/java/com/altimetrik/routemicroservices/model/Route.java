package com.altimetrik.routemicroservices.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Route {
    @Id
    @GeneratedValue(generator = "routeId", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "routeId", initialValue = 44444, sequenceName = "routeId")
    private long routeId;

    @NotBlank(message = "Source is required")
    private String source;

    @NotBlank(message = "Destination is required")
    private String destination;

    @NotNull(message = "Total kilometers must be provided")
    @Positive(message = "Should be Greater than Zero")
    private Double totalKms;
}

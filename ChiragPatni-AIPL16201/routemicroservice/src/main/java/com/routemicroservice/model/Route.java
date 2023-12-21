package com.routemicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int routeId;
    @NotBlank(message = "Please enter the route source.")
    private String source;
    @NotBlank(message = "Please enter the route destination.")
    private String destination;
    @Positive(message = "Total kilometers must be a positive number.")
    private int totalKms;
}
package com.altimetrikfinalproject.routemicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Route {
    @Id
    @GeneratedValue
    private int routeId;
    @NotEmpty(message = "The Source cannot be empty")
    private String source;
    @NotEmpty(message = "The Destination cannot be empty")
    private String destination;
    private int totalKms;
}

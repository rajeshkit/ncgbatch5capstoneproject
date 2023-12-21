package com.railways.route.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Route_Resources")
public class Route {
    @Id
    @Positive(message = "Enter Positive Route Id")
    private long routeId;
    @NotEmpty(message = "Enter Source")
    private String source;
    @NotEmpty(message = "Enter Destination")
    private String destination;
    @Positive(message = "Enter Total Kms")
    private double totalKms;
}

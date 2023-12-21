package com.railways.route.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Route {
    @Id
    @Positive
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long routeId;
    @NotEmpty(message = "Enter source station")
    private String source;
    @NotEmpty(message = "Enter destination")
    private String destination;
    @Positive(message = "Enter total kms from source to destination")
    private long totalKms;


}

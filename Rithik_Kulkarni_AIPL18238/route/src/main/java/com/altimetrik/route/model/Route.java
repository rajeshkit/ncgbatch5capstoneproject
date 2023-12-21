package com.altimetrik.route.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Route {

    @Id
    @GeneratedValue
    private int routeId;

    @NotEmpty(message = "Please Enter the Source")
    private String source;

    @NotEmpty(message = "Please Enter the Destination")
    private String destination;

    @Positive(message = "Please Enter the Distance")
    private int totalKms;

}

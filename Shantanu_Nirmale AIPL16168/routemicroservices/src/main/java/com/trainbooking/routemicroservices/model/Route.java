package com.trainbooking.routemicroservices.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    private int routeId;

    @NotNull @NotEmpty(message = "Please Enter the Source !")
    private String source;

    @NotNull @NotEmpty(message = "Please Enter the Destination !")
    private String destination;

    @NotNull @Positive(message = "Distance Never be Negative !")
    private int totalKms;
}

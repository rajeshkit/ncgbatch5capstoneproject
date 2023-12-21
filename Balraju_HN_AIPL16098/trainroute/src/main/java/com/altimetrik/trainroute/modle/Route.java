package com.altimetrik.trainroute.modle;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

public class Route {
    @Id
    @GeneratedValue
    private int routeId;
    @NotNull(message = "Please enter the source")
    private String source;
    @NotNull(message = "Please enter the destination")
    private String destination;
    @Positive(message = "Enter the positive kms")

    private int totalKms;
}

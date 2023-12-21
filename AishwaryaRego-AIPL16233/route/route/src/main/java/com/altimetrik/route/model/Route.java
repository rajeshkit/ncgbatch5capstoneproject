package com.altimetrik.route.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Route {
    @Id
    @GeneratedValue(generator = "routeId", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "routeId", initialValue = 4620, sequenceName = "routeId")
    private int routeId;

    @NotBlank(message = "Please enter the source name")
    private String Source;
    @NotBlank(message = "Please enter the destination name")
    private String Destination;

    @NotNull(message = "Please enter a value")
    @Min(value = 50,message = "The minimum kms should be 50")
    @Positive(message = "Enter a positive value for the total kms")
    private int totalKms;

}

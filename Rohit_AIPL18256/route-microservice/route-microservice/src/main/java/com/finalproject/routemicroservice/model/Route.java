package com.finalproject.routemicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder

public class Route {
    @Id
    @GeneratedValue

    private int routeId ;

    @NotEmpty(message = "the source field cannot be empty")
    private String source ;


    @NotEmpty(message = "The destination field cannot be empty")
    private String destination ;


    private int totalkms ;
}

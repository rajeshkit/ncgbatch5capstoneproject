package com.altimetrik.schedulemicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Route {

    @Id
    private int routeId;
    private String routeSource;
    private String routeDestination;
    private double totalKm;

}

package com.schedulemicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Route {
    @Id
    private int routeId;
    private String source;
    private String destination;
    private double totalKms;
}

package com.altimetrik.schedulemicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    private int routeId;
    private String source;
    private String destination;
    private double totalKms;

    @Override
    public String toString() {
        return "Route{" +
                "routeId=" + routeId +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", totalKms=" + totalKms +
                '}';
    }
}

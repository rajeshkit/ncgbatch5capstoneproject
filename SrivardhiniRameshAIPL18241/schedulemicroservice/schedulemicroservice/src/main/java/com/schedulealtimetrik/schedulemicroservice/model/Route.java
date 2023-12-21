package com.schedulealtimetrik.schedulemicroservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Route {
    private int routeId;
    private String source;
    private String destination;
    private float totalKms;

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

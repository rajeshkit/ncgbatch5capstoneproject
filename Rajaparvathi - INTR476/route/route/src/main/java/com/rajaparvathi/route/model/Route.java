package com.rajaparvathi.route.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Route {
    @Id
    @GeneratedValue
    private static int routeId;
    private String source;
    private String destination;
    private long totalKms;

    public static int getRouteId(){
        return routeId;
    }


}

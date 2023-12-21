

package com.altimetrik.schedulemicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Route {

    private int routeId;
    private String Source;
    private String Destination;
    private double totalKms;

}

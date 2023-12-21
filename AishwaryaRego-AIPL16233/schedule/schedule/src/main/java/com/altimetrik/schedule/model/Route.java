package com.altimetrik.schedule.model;

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
    private String Source;
    private String Destination;
    private int totalKms;


}

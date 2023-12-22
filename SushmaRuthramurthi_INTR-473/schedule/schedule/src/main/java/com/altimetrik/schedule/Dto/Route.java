package com.altimetrik.schedule.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    private int routeId;
    private String source;
    private String destination;
    private float totalKms;
}

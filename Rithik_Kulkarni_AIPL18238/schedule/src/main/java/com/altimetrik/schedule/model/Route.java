package com.altimetrik.schedule.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Route {

    @Id
    private  int routeId;

    private String source;

    private String destination;

    private int totalKms;


}

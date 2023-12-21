package com.altimetrik.schedule.schedulerestapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Route {
    @Id
    private String routeId;
    private String source;
    private String destination;
    private int totalKmsDistance;
}

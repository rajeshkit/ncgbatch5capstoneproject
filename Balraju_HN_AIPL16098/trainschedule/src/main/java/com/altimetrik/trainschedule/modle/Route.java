package com.altimetrik.trainschedule.modle;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
@Builder
public class Route {
    @Id
    private int routeId;
    private String source;
    private String destination;
    private float totalKms;
}

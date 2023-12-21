package com.altimetrik.schedulemicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import jakarta.persistence.*;

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

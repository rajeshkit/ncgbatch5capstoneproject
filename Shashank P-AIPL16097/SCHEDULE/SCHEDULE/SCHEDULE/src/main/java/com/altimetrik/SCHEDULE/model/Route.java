package com.altimetrik.SCHEDULE.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
    @Builder
    @Component
    public class Route {
        @Id
        private int routeId;
        private String Source;
        private String Destination;
        private int totalKms;

    }


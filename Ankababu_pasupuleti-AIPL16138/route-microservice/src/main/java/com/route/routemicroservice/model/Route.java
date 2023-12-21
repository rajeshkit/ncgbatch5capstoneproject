package com.route.routemicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Route {
    @Id
    @Positive
    private int routeId;
    @NotNull
    private String source;
    @NotNull
    private String destination;
    @Positive
    @NotNull
    private double totalKms;
}

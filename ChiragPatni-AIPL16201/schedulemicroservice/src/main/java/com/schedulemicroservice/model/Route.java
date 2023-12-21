package com.schedulemicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "route_table")
public class Route {
    @Id
    private int routeId;
    private String source;
    private String destination;
    private int totalKms;
    @JsonIgnore
    @OneToOne(mappedBy = "route")
    private Schedule schedule;
}
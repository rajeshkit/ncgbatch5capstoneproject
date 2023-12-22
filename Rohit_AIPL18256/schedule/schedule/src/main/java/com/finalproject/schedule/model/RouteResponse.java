package com.finalproject.schedule.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

@Data
public class RouteResponse {

    @Id
    private int routeId ;

    private String source ;

    private String destination ;

    private int totalKms ;

}

package com.altimetrik.route.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder


public class Route {
    @GeneratedValue
    @Id
    private int routeId;

    private String Source;

    private String Destination;

    private int totalKms;




}


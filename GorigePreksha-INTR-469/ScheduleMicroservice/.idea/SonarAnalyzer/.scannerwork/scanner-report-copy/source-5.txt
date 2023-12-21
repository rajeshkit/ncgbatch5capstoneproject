package com.schedule.schedulemicroservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeNumber;


    @NotNull
    private int routeId;

    @NotEmpty
    private String source;

    @NotEmpty
    private String destination;

    @NotNull
    private double totalKms;


}


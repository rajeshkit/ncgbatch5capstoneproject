package com.altimetrik.route.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Route {
    @Id
    @GeneratedValue
    private int routeId;
    @NotEmpty(message ="please enter source:")
    private String source;
    @NotEmpty(message ="please enter destination:")
    private String destination;
    @NotEmpty(message ="please enter totalKms:")
    private float totalKms;
}

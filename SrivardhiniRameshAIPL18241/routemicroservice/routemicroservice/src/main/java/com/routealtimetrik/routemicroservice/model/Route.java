package com.routealtimetrik.routemicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Route {
    @Id
    @GeneratedValue
    private int routeId;
    @NotEmpty(message = "Please enter the Source")
	private String source;
    @NotEmpty(message = "Please enter the Destination")
	private String destination;
    @Positive(message = "Kms Can Not Be In Negative Value So Give  Positive Value")
	private float totalKms;

}

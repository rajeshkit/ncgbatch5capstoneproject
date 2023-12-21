package com.altimetrik.route.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Route {
    @Id
    @GeneratedValue
    @NotNull(message = "routeid should not be null")
    private int routeId;
    @NotEmpty(message = "source should not be empty")
    private String source;
    @NotEmpty(message = "destination should not be empty")
    private String destination;
    @NotNull(message ="kms must be entered")
    private int totalKms;
}

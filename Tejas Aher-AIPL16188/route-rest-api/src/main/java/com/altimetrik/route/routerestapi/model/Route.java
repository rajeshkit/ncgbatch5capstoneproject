package com.altimetrik.route.routerestapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "route")
public class Route {

    @Id
    @Column(name = "route_id")
    private String routeId;
    @Column(name = "source")
    @NotEmpty(message = "Please Enter Source(Should Not be Empty)")
    private String source;
    @Column(name = "destination")
    @NotEmpty(message = "Please Enter Destination(Should Not be Empty)")
    private String destination;
    @Column(name = "total_kms")
    @NotNull(message = "Please Enter TotalKms(Should Not be Empty)")
    private int totalKmsDistance;
}

package com.altimetrik.routemicroservice.model;
/*  >> In model Class we can define the variables and also create constructor and validation
        on the variable using JAKARTA and LOMBOK Framework
    >> Entity : It is used to schema of the Database based on the variable we defined below.
    >> We can use Jakarta for applying Validation on the variable.
    >> Data : It will create toString() and Hashcode() getter() setter() fucntion for you so that you can use it directly
    >>
 */

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotEmpty;

@NoArgsConstructor
@Data
@Entity
@AllArgsConstructor
@Builder
public class Route {

    @Id
    @GeneratedValue
    private int routeId;
    @NotEmpty
    private String routeSource;
    @NotEmpty
    private String routeDestination;
    @NotNull
    @PositiveOrZero
    private double totalKm;
}

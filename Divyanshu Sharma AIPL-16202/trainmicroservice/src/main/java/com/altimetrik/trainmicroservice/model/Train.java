/*  >> In model Class we can define the variables and also create constructor and validation
        on the variable using JAKARTA and LOMBOK Framework
    >> Entity : It is used to schema of the Database based on the variable we defined below.
    >> We can use Jakarta for applying Validation on the variable.
    >> Data : It will create toString() and Hashcode() getter() setter() fucntion for you so that you can use it directly
    >> @Builder : @Builder, you don't need to write the boilerplate code for creating a separate builder class yourself; instead, Lombok generates it for you.
 */
package com.altimetrik.trainmicroservice.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class Train {

    @NotNull
    @Id
    @GeneratedValue
    private int trainNumber;
    @NotEmpty
    private String trainName;
    @NotNull
    private double totalKm;
    @PositiveOrZero
    private int acCoaches;
    @PositiveOrZero
    private int acCoachesTotalSeats;
    @PositiveOrZero
    private int sleeperCoaches;
    @PositiveOrZero
    private int sleeperCoachesTotalSeats;
    @PositiveOrZero
    private int generalCoaches;
    @PositiveOrZero
    private int generalCoachesTotalSeats;

}

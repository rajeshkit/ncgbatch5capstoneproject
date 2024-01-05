package com.trainbooking.trainmicroservices.model;




import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;

@Data

       /* - It will generate all the getters and setters for all the fields present inside Train class.
          - It also override the toString(), hashCode(), equals() methods */


@NoArgsConstructor                    //It will generate no argument constructor for Train class.
@AllArgsConstructor                  //It will generate all argument constructor
@Entity                             //It will create schema i.e. table structure inside database.
@Builder
public class Train {

    @Id                             //To make trainNumber as Primary Key
    //@GeneratedValue               To auto generate trainNumber starting from 1 (It is like AUTO_INCREMENT in SQL)
    private int trainNumber;

    /*Validation :
          @NotNull : It ensures that field of the table should not hold null value.
          @NotEmpty : It ensures that field of the table should not be empty.
          @Positive : It ensures that field of the table hold only positive values.
          @PositiveOrZero : It ensures that field of the table can hold zero or positive values.*/

    @NotNull @NotEmpty(message = "Please Enter the Train Name !")
    private String trainName;

    @NotNull @Positive(message = "Distance must be positive")
    private int totalKms;

    @NotNull @PositiveOrZero
    private int acCoaches;

    @NotNull @PositiveOrZero
    private int acCoachTotalSeats;

    @NotNull @PositiveOrZero
    private int sleeperCoaches;

    @NotNull @PositiveOrZero
    private int sleeperCoachesTotalSeats;

    @NotNull @Positive
    private int generalCoaches;

    @NotNull @Positive
    private int generalCoachesTotalSeats;
}

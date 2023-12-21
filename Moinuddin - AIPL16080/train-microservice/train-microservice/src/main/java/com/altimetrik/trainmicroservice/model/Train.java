package com.altimetrik.trainmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "trains_information")
public class Train {
    @Id
    @NotNull(message = "Train Number cannot be null")
    @Size(min = 5, max = 5, message = "Train Number must be exactly 5 characters")
    @Column(name = "train_number")
    private String trainNumber;

    @NotNull(message = "Train Name cannot be null")
    private String trainName;

    @PositiveOrZero(message = "Total distance must be a non-negative value")
    private Integer totalDistance;

    @PositiveOrZero(message = "Number of AC coaches must be a non-negative value")
    private Integer numberOfACCoaches;

    @PositiveOrZero(message = "AC coach total seats must be a non-negative value")
    private Integer acCoachTotalSeats;

    @PositiveOrZero(message = "Number of sleeper coaches must be a non-negative value")
    private Integer numberOfSleeperCoaches;

    @PositiveOrZero(message = "Sleeper coach total seats must be a non-negative value")
    private Integer sleeperCoachTotalSeats;

    @PositiveOrZero(message = "Number of general coaches must be a non-negative value")
    private Integer numberOfGeneralCoaches;

    @PositiveOrZero(message = "General coach total seats must be a non-negative value")
    private Integer generalCoachTotalSeats;
}

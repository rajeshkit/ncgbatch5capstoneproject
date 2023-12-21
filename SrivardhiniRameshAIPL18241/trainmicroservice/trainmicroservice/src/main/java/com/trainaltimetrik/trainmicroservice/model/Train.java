package com.trainaltimetrik.trainmicroservice.model;

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
public class Train {
    @Id
    @GeneratedValue
    private int trainNumber;
	@NotEmpty(message = "Please enter the Train Name:")
	private String trainName;
	@Positive(message = "Kms Can Not Be In Negative Value So Give  Positive Value")
	private double totalKms;
	@Positive(message = "No Train Will Be There Without Coaches So Enter A Valid Count")
	private int acCoaches;
	@Positive(message = "No Train Will Be There Without Seats In Coaches So Enter A Valid Count")
	private int acCoachTotalSeats;
	@Positive(message = "No Train Will Be There Without Coaches So Enter A Valid Count")
	private int sleeperCoaches;
	@Positive(message = "No Train Will Be There Without Seats In Coaches So Enter A Valid Count")
	private int sleeperCoachTotalSeats;
	@Positive(message = "No Train Will Be There Without Coaches So Enter A Valid Count")
	private int generalCoaches;
	@Positive(message = "No Train Will Be There Without Seats In Coaches So Enter A Valid Count")
	private int generalCoachTotalSeats;

}

package com.altimetrik.train.trainrestapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class Train {

    @Id
    @Column(name = "t_no")
    @NotEmpty(message = "Please Enter Train Number")
    private String trainNumber;
    @Column(name = "t_name")
    private String trainName;
    @Column(name = "totalKms")
    @NotNull(message = "Please Enter TotalKms(Should Not be Empty)")
    private Integer totalKms;
    @Column(name = "ac_coaches")
    private String acCoaches;
    @Column(name = "ac_totalSeats")
    private Integer acCoachesTotalSeats;
    @Column(name = "gen_coaches")
    private String generalCoaches;
    @Column(name = "gen_seats")
    private Integer generalCoachesTotalSeats;

}

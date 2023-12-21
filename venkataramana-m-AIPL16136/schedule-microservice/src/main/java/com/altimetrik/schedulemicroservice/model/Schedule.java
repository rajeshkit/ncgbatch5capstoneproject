package com.altimetrik.schedulemicroservice.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Schedule {
    @Id
    @GeneratedValue(generator = "scheduleId", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "scheduleId", initialValue = 1, sequenceName = "scheduleId")
    private long scheduleId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "Departure Date and Time is required")
    @FutureOrPresent(message = "Date and Time must be future")
    private Timestamp departureDateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "Arrival Date and Time is required")
    @FutureOrPresent(message = "Date and Time must be future")
    private Timestamp arrivalDateTime;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "train_number")
    @NotEmpty(message = "Train Number is Required")
    private String train;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "route_id")
    @NotEmpty(message = "RouteID Number is Required")
    private String route;
}

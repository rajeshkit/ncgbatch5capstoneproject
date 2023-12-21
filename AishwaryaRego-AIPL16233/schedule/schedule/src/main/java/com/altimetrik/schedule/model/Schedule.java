package com.altimetrik.schedule.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Schedule {
    @Id
    @GeneratedValue(generator="scheduleId",strategy= GenerationType.SEQUENCE)
    @SequenceGenerator(name="scheduleId",initialValue = 270,sequenceName = "scheduleId")
    private int scheduleId;

    @NotBlank(message = "Enter a date and time")
    private String depDateTime;
    @NotBlank(message = "Enter a date and time")
    private String arrDateTime;
    private String train;
    private String route;
}

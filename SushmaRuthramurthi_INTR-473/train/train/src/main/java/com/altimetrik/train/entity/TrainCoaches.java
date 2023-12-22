package com.altimetrik.train.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TrainCoaches {
    @Id
    @GeneratedValue
    private int id;
    @NotEmpty(message="please enter train number: ")
    private int trainnumber;
    @NotEmpty(message="please enter coachtypeid: ")
    private int coachtypeid;
    @NotEmpty(message="please enter coachsize: ")
    private int coachsize;
}

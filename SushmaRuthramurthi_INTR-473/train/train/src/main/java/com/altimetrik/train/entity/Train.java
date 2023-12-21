package com.altimetrik.train.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Train {
    @Id
    @GeneratedValue
    @Column(name = "trainnumber")

    private int trainNumber;
    @Column(name = "trainname")
    @NotEmpty(message = "Please enter train number:")
    private String trainName;

}

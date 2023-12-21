package com.example.trainbooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trackline_master")
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "tracklineSequence",allocationSize = 1, initialValue =20000, sequenceName = "tracklineSequence")
    @Column(name = "a_id")
    private int id;
    @Column(name = "a_name")
    private String name;
    @Column(name = "dop")
    private Date dateOfOperation;

    public Train(String name, Date dateOfOperation) {
        this.name = name;
        this.dateOfOperation = dateOfOperation;
    }

    @OneToMany(mappedBy = "trackline" ,cascade = CascadeType.MERGE)
    private List<TrainDetails> trainDetailsList = new ArrayList<>();
    public void addFlight(TrainDetails trainDetails) {
        this.trainDetailsList.add(trainDetails);
        trainDetails.setTrain(this);
    }
}
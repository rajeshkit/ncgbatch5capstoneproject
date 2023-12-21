package com.train.trainmicroservice.controller;

import com.train.trainmicroservice.model.Train;
import com.train.trainmicroservice.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrainController {
    @Autowired
    private TrainService trainService;

    @PostMapping(value = "/train")
    public ResponseEntity<Train> addTrain(@RequestBody Train train){
        Train train1 = trainService.addTrain(train);
        if(train1!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(train1);
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/train")
    public ResponseEntity<List<Train>> getAllTrains(){
        List<Train> trains=trainService.getAllTrains();
        if(!trains.isEmpty()){
            return ResponseEntity.ok(trains);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping(value = "/train/{trainNumber}")
    public ResponseEntity<Train> getTrainByTrainNo(@PathVariable("trainNumber") int trainNumber){
        Train train= trainService.getTrainByTrainNo(trainNumber);
        if(train!=null){
            return ResponseEntity.ok(train);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
    @PutMapping(value = "/train")
    public ResponseEntity<Train> updateTrain(@RequestBody Train train){
        Train train1=trainService.updateTrain(train);
        if(train1!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(train1);
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DeleteMapping(value = "/train/{trainNumber}")
    public ResponseEntity<Void> deleteTrainByTrainNo(@PathVariable("trainNumber") int trainNumber){
        trainService.deleteTrainByTrainNo(trainNumber);
        return ResponseEntity.noContent().build();
    }

}

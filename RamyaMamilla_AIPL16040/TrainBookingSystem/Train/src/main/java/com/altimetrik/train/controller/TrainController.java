package com.altimetrik.train.controller;

import com.altimetrik.train.exception.TrainNumberNotFoundException;
import com.altimetrik.train.model.Train;
import com.altimetrik.train.service.TrainService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/train-api")
public class TrainController {

    private TrainService trainService;

    @Autowired
    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @PostMapping(value = "/train")
    public Train addTrain(@RequestBody @Valid Train train){
        return trainService.addTrain(train);
    }
    @GetMapping(value = "/train")
    public List<Train> viewAllTrains(){
        return trainService.viewAllTrains();
    }
    @GetMapping(value = "/train/{id}")
    public Train getTrainById(@PathVariable("id") @Valid int trainId) throws TrainNumberNotFoundException{
        return trainService.getTrainById(trainId);
    }
    @PutMapping(value = "/train")
    public Train updateTrain(@RequestBody @Valid Train train){
        return trainService.updateTrain(train);
    }
    @DeleteMapping(value = "/train/{id}")
    public String deleteTrainById(@PathVariable("id") @Valid int trainId){
        return trainService.deleteTrainById(trainId);
    }

}

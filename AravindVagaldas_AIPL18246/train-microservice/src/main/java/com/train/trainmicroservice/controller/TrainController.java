package com.train.trainmicroservice.controller;

import com.train.trainmicroservice.exception.TrainNumberNotExistsException;
import com.train.trainmicroservice.model.Train;
import com.train.trainmicroservice.service.TrainService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/train-api")
public class TrainController {

    private final TrainService trainService;
    @Autowired
    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @PostMapping("/train")
    public Train addTrain(@RequestBody @Valid Train train){
        return trainService.addTrain(train);
    }

    @GetMapping("/train")
    public List<Train> getAllTrains(){
        return  trainService.getAllTrains();

    }

    @GetMapping("/train/{trainNumber}")
    public Train getTrainByTrainNo(@PathVariable("trainNumber") int trainNumber) throws TrainNumberNotExistsException{
        return trainService.getTrainByTrainNo(trainNumber);

    }
    @PutMapping("/train")
    public Train updateTrain(@RequestBody @Valid Train train){
        return trainService.updateTrain(train);

    }

    @DeleteMapping("/train/{trainNumber}")
    public String deleteTrainByTrainNo(@PathVariable("trainNumber") int trainNumber){
        return  trainService.deleteTrainByTrainNo(trainNumber);

    }

}

package com.altimetrik.train.controller;

import com.altimetrik.train.exception.TrainNumberNotExistException;
import com.altimetrik.train.model.Train;
import com.altimetrik.train.service.TrainService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/train-api")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @PostMapping(value = "/train")
    public Train addTrain(@RequestBody @Valid Train train){
        System.out.println("Adding Train....");
        return trainService.addTrain(train);
    }

    @GetMapping(value = "/train")
    public List<Train> getAllTrains(){
        System.out.println("Get All Trains");
        return trainService.getAllTrains();
    }

    @GetMapping(value = "/train/{trainNumber}")
    public Train getTrainByTrainNumber(@PathVariable("trainNumber") int trainNumber) throws TrainNumberNotExistException {
        return trainService.getTrainByTrainNumber(trainNumber);
    }

    @PutMapping(value = "/train")
    public Train updateTrain(@RequestBody Train train) throws TrainNumberNotExistException {
        return trainService.updateTrain(train);
    }

    @DeleteMapping(value = "/train/{trainNumber}")
    public String deleteTrainByTrainNumber(@PathVariable("trainNumber") int trainNumber) throws TrainNumberNotExistException {
        return trainService.deleteTrainByNumber(trainNumber);
    }

}

package com.railways.train.controller;

import com.railways.train.exception.TrainWithThatNumberExists;
import com.railways.train.exception.TrainNumberNotFoundException;
import com.railways.train.model.Train;
import com.railways.train.service.TrainService;
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

    @PostMapping(value="/train")
    public Train addTrain(@RequestBody @Valid Train train) throws TrainWithThatNumberExists {
        return trainService.addTrain(train);
    }
    @GetMapping(value = "/train")
    public List<Train> getAllTrains()
    {
        return trainService.getAllTrains();
    }
    @GetMapping(value = "/train/{id}")
    public Train getTrainById(@PathVariable("id")  long trainNumber) throws TrainNumberNotFoundException {
        return trainService.getTrainById(trainNumber);
    }

    @PutMapping(value = "/train")
    public Train updateTrain(@RequestBody Train train) throws TrainNumberNotFoundException {
        return trainService.updateTrain(train);
    }
    @DeleteMapping(value = "/train/{id}")
    public String deleteTrainById(@PathVariable("id") long trainNumber) throws TrainNumberNotFoundException {
        return trainService.deleteTrainById(trainNumber);
    }

}

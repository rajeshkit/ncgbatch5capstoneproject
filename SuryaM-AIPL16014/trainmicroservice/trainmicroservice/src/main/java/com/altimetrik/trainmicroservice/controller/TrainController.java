package com.altimetrik.trainmicroservice.controller;

import com.altimetrik.trainmicroservice.exception.TrainNumberAlreadyExistsException;
import com.altimetrik.trainmicroservice.exception.TrainNumberNotExistsException;
import com.altimetrik.trainmicroservice.model.Train;
import com.altimetrik.trainmicroservice.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/train-api")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @PostMapping(value = "/train")
    public Train addTrain(@RequestBody @Valid Train train) throws TrainNumberAlreadyExistsException {
        return trainService.addTrain(train);
    }

    @GetMapping(value = "/train")
    public List<Train> getAllTrains() {
        return trainService.getAllTrains();
    }

    @GetMapping(value = "/train/{number}")
    public Train getTrainByNumber(@PathVariable("number") int trainNumber) throws TrainNumberNotExistsException {
        return trainService.getTrainByNumber(trainNumber);
    }

    @PutMapping(value = "/train")
    public Train updateTrain(@RequestBody @Valid Train train) throws TrainNumberNotExistsException {
        return trainService.updateTrain(train);
    }

    @DeleteMapping(value = "/train/{number}")
    public String deleteTrainByNumber(@PathVariable("number") int trainNumber) throws TrainNumberNotExistsException {
        return trainService.deleteTrainByNumber(trainNumber);
    }
}

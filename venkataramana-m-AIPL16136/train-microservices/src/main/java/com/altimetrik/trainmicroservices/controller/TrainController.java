package com.altimetrik.trainmicroservices.controller;

import com.altimetrik.trainmicroservices.exception.TrainNumberAlreadyExistsException;
import com.altimetrik.trainmicroservices.exception.TrainNumberNotExistsException;
import com.altimetrik.trainmicroservices.model.Train;
import com.altimetrik.trainmicroservices.service.TrainService;
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

    @GetMapping(value = "/train/{trainNumber}")
    public Train getTrainByTrainNumber(@PathVariable("trainNumber") long trainNumber) throws TrainNumberNotExistsException {
        return trainService.getTrainByTrainNumber(trainNumber);
    }

    @PutMapping(value = "/train")
    public Train updateTrain(@RequestBody Train train) throws TrainNumberNotExistsException {
        return trainService.updateTrain(train);
    }

    @DeleteMapping(value = "/train/{trainNumber}")
    public String deleteTrainByTrainNumber(@PathVariable("trainNumber") long trainNumber) throws TrainNumberNotExistsException {
        return trainService.deleteTrainByTrainNumber(trainNumber);
    }

}


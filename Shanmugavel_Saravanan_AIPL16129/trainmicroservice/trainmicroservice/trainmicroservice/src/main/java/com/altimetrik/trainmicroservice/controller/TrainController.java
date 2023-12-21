package com.altimetrik.trainmicroservice.controller;


import com.altimetrik.trainmicroservice.exception.TrainNumberAlreadyExists;
import com.altimetrik.trainmicroservice.exception.TrainNumberNotFoundException;
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
    public Train addTrain(@RequestBody @Valid Train train) throws TrainNumberAlreadyExists {//throw new MethodInvalidArgException();
        return trainService.addTrain(train);
    }
    @GetMapping(value = "/train")
    public List<Train> getTrainByNumber() {
        return trainService.getAllTrain();
    }
    @GetMapping(value = "/train/{number}")
    public Train getTrainByNumber(@PathVariable("number") int trainNumber) throws TrainNumberNotFoundException {
        return trainService.getTrainByNumber(trainNumber);
    }
    @PutMapping(value = "/train")
    public Train updateTrain(@RequestBody @Valid Train train) throws TrainNumberNotFoundException {
        return trainService.updateTrain(train);
    }
    @DeleteMapping(value = "/train/{number}")
    public String deleteTrainById(@PathVariable("number") int trainNumber) throws TrainNumberNotFoundException {
        return trainService.deleteTrainByNumber(trainNumber);
    }
}

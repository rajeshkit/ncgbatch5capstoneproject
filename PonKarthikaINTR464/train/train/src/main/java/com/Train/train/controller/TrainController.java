package com.Train.train.controller;



import com.Train.train.exception.TrainNumberNotFound;
import com.Train.train.model.Train;
import com.Train.train.service.TrainService;
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
    public Train addTrain(@RequestBody Train train) {// throw new MethodInvalidArgException();
        return trainService.addTrain(train);
    }
    @GetMapping(value = "/train")
    public List<Train> getAllTrains() {
        return trainService.getAllTrains();
    }
    @GetMapping(value = "/train/{Number}")
    public Train getTrainByTrainNumber(@PathVariable("Number") int trainNumber)  {

            return trainService.getTrainByTrainNumber(trainNumber);

    }
    @PutMapping(value = "/train")
    public Train updateTrain(@RequestBody  Train train)  {

            return trainService.updateTrain(train);

    }
    @DeleteMapping(value = "/train/{Number}")
    public String deleteTrainByTrainNumber(@PathVariable("Number") int trainNumber) throws TrainNumberNotFound {
        return trainService.deleteTrainByTrainNumber(trainNumber);
    }
}


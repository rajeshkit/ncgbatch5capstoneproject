package com.altimetrik.train.controller;

import com.altimetrik.train.exception.TrainIdNotExistsException;
import com.altimetrik.train.model.Train;
import com.altimetrik.train.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/train")
public class TrainController {
    @Autowired
    private TrainService trainService;

    @GetMapping("/{trainNumber}")
    public Train getTrainByNumber(@PathVariable int trainNumber) throws TrainIdNotExistsException {
        return trainService.getTrainByNumber(trainNumber);
    }


    @PostMapping(value = "/train")
    public Train addTrain(@RequestBody @Valid Train train) {

        return trainService.addTrain(train);
    }

    @GetMapping(value = "/train")
    public List<Train> getAllTrains() {

        return trainService.getAllTrains();
    }

    @PutMapping(value = "/train")
    public Train updateTrain(@RequestBody Train train) throws TrainIdNotExistsException {
        return trainService.updateTrain(train);
    }
    @DeleteMapping(value = "/train/{id}")
    public String deleteTrainByNumber(@PathVariable("id") int trainNumber) throws TrainIdNotExistsException {
        return trainService.deleteTrainByNumber(trainNumber);
    }
}


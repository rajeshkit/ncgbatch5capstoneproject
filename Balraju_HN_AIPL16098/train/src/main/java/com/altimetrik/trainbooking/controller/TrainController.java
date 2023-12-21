package com.altimetrik.trainbooking.controller;
import com.altimetrik.trainbooking.exception.NoSuchElementException;
import com.altimetrik.trainbooking.service.TrainService;

import com.altimetrik.trainbooking.modle.Train;
import com.altimetrik.trainbooking.service.TrainService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/train-api")
public class TrainController {
    @Autowired
    private TrainService trainService;
    @PostMapping(value = "/train")
    public Train addTrain(@RequestBody @Valid Train train){

        return trainService.addTrain(train);

    }

    @GetMapping(value = "/train")
    public List<Train> getAllTrain(){
        return trainService.getAllTrain();

    }
    @GetMapping(value = "/train/{id}")
    public Train getTrainByNumbe(@PathVariable("id")int trainNumner) throws NoSuchElementException {
        return trainService.getTrainByNumber(trainNumner);

    }
    @PutMapping(value = "/train")
    public Train updatingTrain(@RequestBody Train train) throws NoSuchElementException{
        return trainService.updateTrain(train);
    }
    @DeleteMapping(value = "/train/{id}")
    public String  deleteTrainByNumber(@PathVariable("id")int trainNumber) throws NoSuchElementException{
        return trainService.deleteTrainByNumber(trainNumber);
    }
}

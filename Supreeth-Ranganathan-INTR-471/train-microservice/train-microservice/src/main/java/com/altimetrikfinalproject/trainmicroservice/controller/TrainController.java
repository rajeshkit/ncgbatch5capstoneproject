package com.altimetrikfinalproject.trainmicroservice.controller;

import com.altimetrikfinalproject.trainmicroservice.exception.TrainDoesNotExistException;
import com.altimetrikfinalproject.trainmicroservice.model.Train;
import com.altimetrikfinalproject.trainmicroservice.service.TrainService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return trainService.getAllTrains();
    }
    @GetMapping(value = "/train/{trainNumber}")
    public Optional<Optional> getTrainById(@PathVariable int trainNumber) throws TrainDoesNotExistException {
        return trainService.getTrainByID(trainNumber);
    }
    @PutMapping(value = "/train")
    public Train updateTrain(@RequestBody @Valid Train train) throws TrainDoesNotExistException {
        return trainService.updateTrain(train);
    }

    @DeleteMapping(value ="/train/{trainNumber}")
    public String deleteTrain(@PathVariable int trainNumber) throws TrainDoesNotExistException{
        trainService.deleteTrain(trainNumber);
        return "Train has been deleted from the table";
    }

}

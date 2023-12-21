package com.altimetrik.trainmicroservice.controller;

import com.altimetrik.trainmicroservice.exception.TrainIdNotExistsException;
import com.altimetrik.trainmicroservice.model.Train;
import com.altimetrik.trainmicroservice.service.TrainService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Train-api")
public class TrainController {
    private static final Logger logger = LoggerFactory.getLogger(TrainController.class);
    @Autowired
    private TrainService trainService;
    @PostMapping(value="/train")
    public Train addTrain(@RequestBody @Valid Train train){
        logger.info("Received request to add train: {}", train);
        return trainService.addTrain(train);
    }
    @GetMapping(value="/train")
    public List<Train> getAllTrain(){
        logger.info("Received request to get all trains");
        return trainService.getAllTrain();
    }
    @GetMapping(value="/train/{id}")
    public Train getTrainById(@PathVariable("id") int trainId) throws TrainIdNotExistsException {
        logger.info("Received request to get train by ID: {}", trainId);
        return trainService.getTrainById(trainId);
    }
    @PutMapping(value="/train")
    public Train updateTrain(@RequestBody Train train) throws TrainIdNotExistsException {
        logger.info("Received request to update train: {}", train);
        return trainService.updateTrain(train);
    }
    @DeleteMapping(value="/train/{id}")
    public String deleteTrainById(@PathVariable("id") int trainId) throws TrainIdNotExistsException {
        logger.info("Received request to delete train by ID: {}", trainId);
        return trainService.deleteTrainById(trainId);
    }
}

package com.altimetrik.Train.controller;

import com.altimetrik.Train.exception.TrainIdNotExistsException;
import com.altimetrik.Train.model.Train;
import com.altimetrik.Train.service.TrainService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/train-api")
public class TrainController {

    private static final Logger logger = Logger.getLogger(TrainController.class.getName());

    @Autowired
    private TrainService trainService;
    @PostMapping(value="/train")
    public Train addTrain(@RequestBody @Valid Train train){
        logger.info("Adding a new train: " + train.getTrainName());

        return trainService.addTrain(train);
    }
    @GetMapping(value="/train")
    public List<Train> getAllTrains(){
        logger.info("Fetching all trains");
        return trainService.getAllTrains();
    }
    @GetMapping(value="/train/{id}")
    public Train getTrainById(@PathVariable("id") int trainId) throws TrainIdNotExistsException {
        logger.info("Fetching train by ID: " + trainId);
        return trainService.getTrainById(trainId);
    }
    @PutMapping(value="/train")
//    @ResponseBody
    public Train updateTrain(@RequestBody Train train) throws TrainIdNotExistsException {
        logger.info("Updating train with ID: " + train.getTrainId());
        return trainService.updateTrain(train);
    }
    @DeleteMapping(value="/train/{id}")
//    @ResponseBody
    public String deleteTrainById(@PathVariable("id") int trainId) throws TrainIdNotExistsException {
        logger.info("Deleting train with ID: " + trainId);
        return trainService.deleteTrainById(trainId);
    }
}
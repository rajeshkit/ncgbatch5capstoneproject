package com.train.trainmicroservice.controller;

import com.train.trainmicroservice.exception.TrainIdNotExistException;
import com.train.trainmicroservice.model.Train;
import com.train.trainmicroservice.service.TrainService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/train-api")
public class TrainController {
    @Autowired
    TrainService trainService;
    @PostMapping(value = "/train")
    public Train addTrain(@RequestBody @Valid Train train) {
        log.info("Request sent to service from add train");
        return trainService.addTrain(train);
    }

    @GetMapping(value = "/train")
    public List<Train> getAllTrain() {
        log.info("Request sent to service for get all train");
        return trainService.getAllTrain();
    }

    @GetMapping(value = "/train/{id}")
    public Train getTrainById(@PathVariable("id") int trainNumber) throws TrainIdNotExistException {
        log.info("Request sent to service for get train by id");
        return trainService.getTrainById(trainNumber);
    }


    @PutMapping(value = "/train")
    public Train updateTrain(@RequestBody Train train) throws TrainIdNotExistException {
        log.info("Request sent to service for update train detail");
        return trainService.updateTrain(train);
    }

@DeleteMapping(value = "/train/{id}")
    public String deleteTrainById(@PathVariable("id") int trainId) throws TrainIdNotExistException {
   String string= trainService.deleteTrainById(trainId);
    log.info("Train Deleted");
    return string;
    }
}

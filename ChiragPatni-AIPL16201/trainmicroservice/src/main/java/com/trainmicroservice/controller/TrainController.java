package com.trainmicroservice.controller;

import com.trainmicroservice.exception.TrainNumberDoesNotExistException;
import com.trainmicroservice.model.Train;
import com.trainmicroservice.service.TrainService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/train-api")
public class TrainController {

    @Autowired
    private TrainService trainService;
    private static final Logger logger = LoggerFactory.getLogger(TrainController.class);
    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @PostMapping("/train")
    public Train saveTrainDetails(@RequestBody @Valid Train train) {
        logger.info("Received a request to save train details : {}", train);
        return trainService.saveTrainDetails(train);
    }

    @GetMapping("/train")
    public List<Train> getAllTrainsDetail() {
        logger.info("Received a request to get all trains detail.");
        return trainService.getAllTrainsDetail();
    }

    @GetMapping("/{id}")
    public Train getTrainDetailsById(@PathVariable("id") int trainNumber) throws TrainNumberDoesNotExistException {
        logger.info("Received a request to get train details by Train-Number : {}", trainNumber);
        return trainService.getTrainDetailsById(trainNumber);
    }

    @PutMapping("/train")
    public Train updateTrainDetails(@RequestBody  Train train) throws TrainNumberDoesNotExistException {
        logger.info("Received a request to update train details : {}", train);
        return trainService.updateTrainDetails(train);
    }

    @DeleteMapping("/{id}")
    public String deleteTrainDetails(@PathVariable("id") int trainNumber) throws TrainNumberDoesNotExistException {
        logger.info("Received a request to delete train details with Train-Number : {}", trainNumber);
        return trainService.deleteTrainDetails(trainNumber);
    }
}
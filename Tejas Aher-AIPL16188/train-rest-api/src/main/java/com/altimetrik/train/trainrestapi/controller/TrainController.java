package com.altimetrik.train.trainrestapi.controller;

import com.altimetrik.train.trainrestapi.exception.TrainIDNotExistsException;
import com.altimetrik.train.trainrestapi.model.Train;
import com.altimetrik.train.trainrestapi.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/train-api")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @PostMapping(value = "/train")
    public ResponseEntity<Train> addTrain(@RequestBody Train train) {

        trainService.addTrain(train);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(train.getTrainNumber()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/train")
    public List<Train> getAllTrains() {

        return trainService.getAllTrains();
    }

    @GetMapping(value = "/train/{id}")
    public Train getTrainByTrainID(@PathVariable("id") String trainID) throws TrainIDNotExistsException {

        return trainService.getTrainByTrainID(trainID);
    }

    @PutMapping(value = "/train/{id}")
    public Train updateTrain(@PathVariable("id") String trainId, @RequestBody Train train) throws TrainIDNotExistsException {

        return trainService.updateTrain(train, trainId);
    }

    @DeleteMapping(value = "/train/{id}")
    public String deleteTrainByTrainID(@PathVariable("id") String trainId) throws TrainIDNotExistsException {

        return trainService.deleteTrainByTrainID(trainId);

    }

}

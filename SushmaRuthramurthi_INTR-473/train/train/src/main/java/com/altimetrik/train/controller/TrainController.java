package com.altimetrik.train.controller;

import com.altimetrik.train.dto.TrainCoachResponse;
import com.altimetrik.train.dto.TrainResponse;
import com.altimetrik.train.entity.Train;
import com.altimetrik.train.entity.TrainCoaches;
import com.altimetrik.train.exception.TrainNotExistsException;
import com.altimetrik.train.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/train")
public class TrainController {
    private TrainService trainService;

    @Autowired
    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @PostMapping
    public ResponseEntity<TrainResponse> addTrain(@RequestBody @Valid Train train) {
        return new ResponseEntity<>(trainService.addTrain(train), HttpStatus.CREATED);
    }

    @PostMapping("/{trainNumber}/coaches")
    public ResponseEntity<List<TrainCoachResponse>> addTrainCoaches(@PathVariable("trainNumber") int trainNumber,
                                                                    @RequestBody TrainCoaches trainCoaches) {
        return new ResponseEntity<>(trainService.addTrainCoaches(trainNumber, trainCoaches), HttpStatus.CREATED);
    }

    @GetMapping("/{trainNumber}")
    public ResponseEntity<TrainResponse> getTrainDetials(@PathVariable("trainNumber") int trainNumber,
                                                         @RequestParam(value = "includecoaches", required = false) boolean includecoaches) {
        return new ResponseEntity<>(trainService.getTrainDetials(trainNumber, includecoaches), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<TrainResponse> updateTrain(@RequestBody Train train) throws TrainNotExistsException {
        return new ResponseEntity<>(trainService.updateTrain(train), HttpStatus.CREATED);
    }

    @DeleteMapping("/{trainNumber}")
    public ResponseEntity<String> deleteTrain(@PathVariable("trainNumber") int trainNumber) throws TrainNotExistsException {
        return new ResponseEntity<>(trainService.deletaTrain(trainNumber),HttpStatus.OK);
    }
}

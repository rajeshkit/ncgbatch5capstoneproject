package com.altimetrik.trainbookingsystem.controller;

import com.altimetrik.trainbookingsystem.exception.TrainNotExistsException;
import com.altimetrik.trainbookingsystem.model.Train;
import com.altimetrik.trainbookingsystem.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/train-api")

public class TrainController {
    @Autowired
    private TrainService trainService;
    @PostMapping(value = "/train")
    public Train addTrain(@RequestBody @Valid Train train) {// throw new MethodInvalidArgException();
        return trainService.addTrain(train);
    }
    @GetMapping(value = "/train")
    public List<Train> getAlltrain() {
        return trainService.getAlltrain();
    }
    @GetMapping(value = "/train/{no}")
    public Train getTrainByNo(@PathVariable("no") int trainNo) throws TrainNotExistsException {
        return trainService.getTrainByNo(trainNo);
    }
    @PutMapping(value = "/train")
    public Train updateTrain(@RequestBody  Train train) throws TrainNotExistsException {
        return trainService.updateTrain(train);
    }
    @DeleteMapping(value = "/train/{no}")
    public String deleteTrainByNo(@PathVariable("no") int trainNo) throws TrainNotExistsException {
        return trainService.deleteTrainByNo(trainNo);
    }


}

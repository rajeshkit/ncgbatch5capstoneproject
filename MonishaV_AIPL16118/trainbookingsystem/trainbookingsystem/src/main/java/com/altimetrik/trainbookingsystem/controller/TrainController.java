package com.altimetrik.trainbookingsystem.controller;

import com.altimetrik.trainbookingsystem.exception.TrainNoNotExistsException;
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
        return trainService.getAllTrain();
    }
    @GetMapping(value = "/train/{no}")
    public Train getTrainByNo(@PathVariable("no") String trainNo) throws TrainNoNotExistsException {
        return trainService.getTrainByNo(trainNo);
    }
    @PutMapping(value = "/train")
    public Train updateTrain(@RequestBody  Train train) throws TrainNoNotExistsException {
        return trainService.updateTrain(train);
    }
    @DeleteMapping(value = "/train/{no}")
    public String deleteTrainByNo(@PathVariable("no") String trainNo) throws TrainNoNotExistsException {
        return trainService.deleteTrainByNo(trainNo);
    }


}

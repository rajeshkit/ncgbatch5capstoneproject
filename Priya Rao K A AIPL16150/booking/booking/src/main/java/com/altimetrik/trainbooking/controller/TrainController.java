package com.altimetrik.trainbooking.controller;
import com.altimetrik.trainbooking.exception.TrainNumberNotExistsException;
import com.altimetrik.trainbooking.model.Train;
import com.altimetrik.trainbooking.service.TrainService;
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
    public Train getTrainbyNumber(@PathVariable("no") int trainNumber) throws TrainNumberNotExistsException{
        return trainService.getTrainByNumber(trainNumber);

    }
    @PutMapping(value = "/train")
    public Train updateTrain(@RequestBody  Train train) throws TrainNumberNotExistsException {
        return trainService.updateTrain(train);
    }
    @DeleteMapping(value = "/train/{no}")
    public String deleteTrainByNumber(@PathVariable("no") int trainNumber) throws TrainNumberNotExistsException {
        return trainService.deleteTrainByNumber(trainNumber);
    }


}
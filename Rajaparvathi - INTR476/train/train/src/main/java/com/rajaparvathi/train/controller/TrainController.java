package com.rajaparvathi.train.controller;

import com.rajaparvathi.train.model.Train;
import com.rajaparvathi.train.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/train-api")

public class TrainController {
    @Autowired
    private TrainService trainService;
    @PostMapping(value = "/train")
    private Train addTrainDetails(@RequestBody Train train){
        return trainService.addTrainDetails(train);
    }
    @GetMapping(value = "/train")
    private List<Train> getAllTrainDetails(){
        return trainService.getAllTrainDetails();
    }
    @GetMapping(value = "/train/{id}")
    private Train SearchTrainByNumber(@PathVariable("id") int trainNumber){
        return trainService.searchTrainByNumber(trainNumber);
    }
    @PutMapping(value = "/train")
    public Train updateDetails(@RequestBody  Train train){
        return trainService.updateDetails(train);
    }
    @DeleteMapping(value = "/train/{id}")
    public String removeTrainByNumber(@PathVariable("id") int trainNumber){
        return trainService.removeTrainByNumber(trainNumber);
    }
}

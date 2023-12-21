package com.railwaybooking.Train.controller;

import com.railwaybooking.Train.exception.TrainNumberNotExistException;
import com.railwaybooking.Train.model.TrainInfo;
import com.railwaybooking.Train.service.TrainService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainInfo-api")
public class TrainController {
    @Autowired
    private TrainService trainService;
    @PostMapping(value="/trainInfo")
    public TrainInfo addTrain(@RequestBody @Valid TrainInfo trainInfo){

        return trainService.addTrain(trainInfo);
    }
    @GetMapping(value="/trainInfo")
    public List<TrainInfo> getAllTrains(){
        return trainService.getAllTrains();
    }
    @GetMapping(value="/trainInfo/{number}")
    public TrainInfo getTrainByNumber(@PathVariable("number") long trainNumber) throws TrainNumberNotExistException {
        return trainService.getTrainByNumber(trainNumber);
    }

    @PutMapping(value="/trainInfo")
    public TrainInfo updateTrainInfo(@RequestBody TrainInfo trainInfo) throws TrainNumberNotExistException{
        return trainService.updateTrainInfo(trainInfo);
    }

    @DeleteMapping(value = "/trainInfo/{number}")
    public String deleteTrainByNumber(@PathVariable("number") long trainNumber) throws TrainNumberNotExistException{
        return trainService.deleteTrainByNumber(trainNumber);
    }



}

package com.booking_details.train.controller;

import com.booking_details.train.exception.TrainIdNotFoundException;
import com.booking_details.train.model.TrainModel;
import com.booking_details.train.service.TrainService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/train-microservice")
public class TrainController {
    @Autowired
    private TrainService trainService;
    @PostMapping(value = "/train")
    public TrainModel addTrainDetails(@RequestBody @Valid TrainModel trainModel)
    {
        return trainService.addTrainDetails(trainModel);
    }

    @GetMapping(value = "/train")
    public List<TrainModel> getAllTrainDetails()
    {
        return trainService.getAllTrainDetails();
    }

    @GetMapping(value = "/train/{id}")
    public TrainModel getAllTrainDetailsById(@PathVariable("id") Long trainNumber) throws TrainIdNotFoundException {
        return trainService.getAllTrainDetailsById(trainNumber);
    }

    @PutMapping(value = "/train")
    public TrainModel updateTrainDetails(@RequestBody TrainModel trainModel) throws TrainIdNotFoundException {
        return trainService.updateTrainDetails(trainModel);
    }

    @DeleteMapping(value = "/train/{id}")
    public String deleteTrainDetailsById(@PathVariable("id") Long trainNumber) throws TrainIdNotFoundException {
        return trainService.deleteTrainDetailsById(trainNumber);
    }
}

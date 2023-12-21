package com.booking.train.controller;

import com.booking.train.exception.TrainNumberNotExistsException;
import com.booking.train.model.TrainResources;
import com.booking.train.service.TrainService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/train-api")
public class TrainController {

    private TrainService trainService;
    @Autowired
    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @PostMapping(value = "/train")
    public TrainResources addTrainResources(@RequestBody @Valid TrainResources trainResources)
    {
        return trainService.addTrainResources(trainResources);
    }

    @GetMapping(value = "/train")
    public List<TrainResources> getAllTrainResources()
    {
        return trainService.getAllTrainResources();
    }

    @GetMapping(value = "/train/{id}")
    public TrainResources getTrainResourcesById(@PathVariable("id") Long trainNumber) throws TrainNumberNotExistsException {
        return trainService.getAllTrainResourcesById(trainNumber);
    }

    @PutMapping(value = "/train")
    public TrainResources updateTrainResources(@RequestBody TrainResources trainResources) throws TrainNumberNotExistsException {
        return trainService.updateTrainResource(trainResources);
    }

    @DeleteMapping(value = "/train/{id}")
    public String deleteTrainResourcesById(@PathVariable("id") Long trainNumber) throws TrainNumberNotExistsException {
        return trainService.deleteTrainResourceByTrainNumber(trainNumber);
    }
}

package com.Booking.train.controller;

import com.Booking.train.customexception.TrainIdNotFoundException;
import com.Booking.train.model.TrainResources;
import com.Booking.train.services.TrainService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/train-api")
public class TrainController {
    @Autowired
    private TrainService trainService;
    @PostMapping(value="/Train_Table")
    public TrainResources addTrain(@RequestBody @Valid TrainResources trainResources){
        System.out.println(trainResources);
        return trainService.addTrain(trainResources);
    }
    @GetMapping(value="/Train_Table")
    public List<TrainResources> getAllTrainDetail(){

        return trainService.getAllTrainDetail();
    }
    @GetMapping(value="/Train_Table/{id}")
    public TrainResources getTrainById(@PathVariable("id") Long trainId) throws TrainIdNotFoundException {

        return trainService.getTrainById(trainId);
    }
    @PutMapping(value = "/Train_Table")
    public TrainResources updateTrain(@RequestBody TrainResources trainResources) throws TrainIdNotFoundException
    {
        return trainService.updateTrain(trainResources);
    }
    @DeleteMapping(value="/Train_Table/{id}")
    public String deleteTrainById(@PathVariable("id") Long trainId) throws TrainIdNotFoundException {

        return trainService.deleteTrainById(trainId);
    }
}

package com.TrainBookingSystem.Train.Controller;

import com.TrainBookingSystem.Train.Exception.TrainIdNotFoundException;
import com.TrainBookingSystem.Train.Services.TrainService;
import com.TrainBookingSystem.Train.model.TrainResources;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/train-api")
public class TrainController {

    @Autowired
    private TrainService trainService;
    @PostMapping(value="/train")
    public ResponseEntity<TrainResources> addTrain(@RequestBody @Valid TrainResources trainResources){

        TrainResources trainResources1=trainService.addTrainResources(trainResources);
        return ResponseEntity.status(HttpStatus.OK).body(trainResources1);
    }
    @GetMapping(value="/train")
    public List<TrainResources> allTrainDetail(){

        return trainService.getAllTrainDetail();
    }

    @GetMapping(value="/train/{id}")
    public ResponseEntity<TrainResources> getTrainById(@PathVariable("id") Long trainId) throws TrainIdNotFoundException {

        TrainResources trainResources= trainService.getTrainById(trainId);
        return ResponseEntity.status(HttpStatus.OK).body(trainResources);
    }

    @PutMapping(value = "/train")
    public TrainResources updateTrainDetail(@RequestBody TrainResources trainResources) throws TrainIdNotFoundException {


        return trainService.updateTrainDetail(trainResources);
    }

    @DeleteMapping(value="/train/{id}")
    public String deleteTrainById(@PathVariable("id") Long trainId) throws TrainIdNotFoundException {

        return trainService.deleteTrainById(trainId);
    }


}

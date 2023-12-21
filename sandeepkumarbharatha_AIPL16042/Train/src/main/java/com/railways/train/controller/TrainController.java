package com.railways.train.controller;

import com.railways.train.exceptions.TrainNumberNotFound;
import com.railways.train.model.Train;
import com.railways.train.services.TrainServices;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/train-api")
public class TrainController {
    @Autowired
    private TrainServices trainServices;

    @PostMapping(value = "/traindetails")
    public Train trainDetails(@RequestBody @Valid Train train) {
        return trainServices.addTrainDetails(train);
    }

    @GetMapping(value = "/traindetails/{id}")
    public Train getTrainByNumber(@PathVariable("id") int trainNumToSearch) throws TrainNumberNotFound {
        return trainServices.getTrainByNumber(trainNumToSearch);
    }

    @GetMapping(value = "/traindetails")
    public List<Train> getAllDetails() {
        return trainServices.getAllDetails();
    }

    @PutMapping(value = "/traindetails")
    public Train updateTainDetails(@RequestBody @Valid Train train) throws TrainNumberNotFound {
        return trainServices.updateTainDetails(train);
    }

    @DeleteMapping(value = "/traindetails/{id}")
    public String deleteByTrainNumber(@PathVariable("id") long trainNumberToDelete) throws  TrainNumberNotFound{
        return trainServices.deleteByTrainNumber(trainNumberToDelete);
    }


}

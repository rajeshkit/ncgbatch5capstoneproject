package com.project.Railways.controller;

import com.project.Railways.exception.TrainNotFoundException;
import com.project.Railways.model.Railway;
import com.project.Railways.service.RailwayService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/railways-api")

public class RailwaysController {

    @Autowired
    private RailwayService railwayService ;

    public RailwaysController(RailwayService railwayService) {
        this.railwayService = railwayService;
    }


    @PostMapping(value = "/railways")
    public Railway addTrain(@RequestBody @Valid Railway railway)
    {
        return railwayService.addTrain(railway);
    }

    @GetMapping(value = "/railways")
    public List<Railway> getAllTrains()
    {
        return railwayService.getAllTrains();
    }

    @GetMapping(value = "/railways/{trainNumber}")
    public Optional<Optional> getTrainById(@PathVariable int trainNumber) throws TrainNotFoundException
    {
        return railwayService.getTrainById(trainNumber);
    }

    @PutMapping(value = "/railways")
    public Railway updateTrain(@RequestBody @Valid Railway railway) throws TrainNotFoundException{
        return railwayService.updateTrain(railway);
    }

    @DeleteMapping(value = "/railways/{trainNumber}")
    public String deleteTrain(@PathVariable int trainNumber) throws TrainNotFoundException{
        railwayService.deleteTrain(trainNumber);
        return "Train is deleted from the table ";
    }
}

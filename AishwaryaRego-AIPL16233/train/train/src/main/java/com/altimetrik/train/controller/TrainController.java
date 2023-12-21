package com.altimetrik.train.controller;

import com.altimetrik.train.exception.TrainAlreadyExistsException;
import com.altimetrik.train.exception.TrainNotExistsException;
import com.altimetrik.train.model.Train;
import com.altimetrik.train.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
public class TrainController {
    @Autowired
    private TrainService trainService;
    public TrainController(TrainService trainService){
        this.trainService=trainService;
    }
    @RequestMapping(value="/train-api/train/",method= RequestMethod.GET)
    @ResponseBody
    public List<Train> getAllTrains(){
        return trainService.getAllTrains();
    }
    @RequestMapping(value="/train-api/train/{num}",method= RequestMethod.GET)
    @ResponseBody
    public Train getTrainByNum(@PathVariable("num") int trainNum) throws TrainNotExistsException {
        return trainService.getTrainByNum(trainNum);
    }
    @RequestMapping(value="/train-api/train/",method= RequestMethod.POST)
    @ResponseBody
    public Train addTrain(@RequestBody @Valid Train train) throws TrainAlreadyExistsException {
        return trainService.addTrain(train);
    }
    @RequestMapping(value="/train-api/train",method= RequestMethod.PUT)
    @ResponseBody
    public Train updateTrain(@RequestBody Train train) throws TrainNotExistsException {
        return trainService.updateTrain(train);
    }
    @RequestMapping(value="/train-api/train/{num}",method= RequestMethod.DELETE)
    @ResponseBody
    public String deleteTrain(@PathVariable("num") int trainNum) throws TrainNotExistsException{
        return trainService.deleteTrain(trainNum);
    }
}

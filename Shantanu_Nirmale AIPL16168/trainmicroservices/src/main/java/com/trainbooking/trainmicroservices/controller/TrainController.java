package com.trainbooking.trainmicroservices.controller;

import com.trainbooking.trainmicroservices.exception.TrainNumberNotExistException;
import com.trainbooking.trainmicroservices.model.Train;
import com.trainbooking.trainmicroservices.service.TrainService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  //It will automatically assign @ResponseBody before each method(mapping).
@RequestMapping("/train-api")  //Each Mapping has common path "/train-api" so that we can use this annotation on top of class to avoid repeatation of code
public class TrainController {

    @Autowired  //It creates/injects the bean of TrainServiceImpl(Base class of TrainService(I)) inside IOC container.
    private TrainService trainService;


    /*@RequestMapping(value = "/train-api/train", method = RequestMethod.POST)
    instead of writing method = RequestMethod.POST we can directly use @PostMapping annotation*/

    //To create Train resource
    @PostMapping(value = "/train")
    public Train addTrain(@RequestBody @Valid Train train){
        return trainService.addTrain(train);
    }

    //To get all train details.
    @GetMapping(value = "/train")

    public List<Train> getAllTrainDetails(){
        return trainService.getAllTrainDetails();
    }

    //To get all train details.
    @GetMapping(value = "/train/{trainNumber}")
    //@PathVariable : trainNumber (Which Train Details you want) its dynamic
    public Train getTrainByTrainNumber(@PathVariable("trainNumber") int trainNumber) throws TrainNumberNotExistException {
        return trainService.getTrainByTrainNumber(trainNumber);
    }

    //To update train details.
    @PutMapping(value = "/train")
    public Train updateTrainDetails(@RequestBody @Valid Train train) throws TrainNumberNotExistException {
        return trainService.updateTrainDetails(train);
    }

    //To delete train details by using train number
    @DeleteMapping(value = "/train/{trainNumber}")
    public String deleteTrainByTrainNumber(@PathVariable("trainNumber") int trainNumber) throws TrainNumberNotExistException {
        return trainService.deleteTrainByTrainNumber(trainNumber);
    }

}

package com.altimetrik.trainmicroservice.Controller;
/*
>> This is the Controller class we can write methods here or the REST API End points
>> @Controller: It is used organize and manage request-handling components in a Spring MVC application
>> @RestController : We can use this Annotation so that we don't need to write @ResponseBody again & again
>> @RequestMapping :  We can use this outside the class and add common path of API into it so that we don't need to write it again & again
>> @Autowired :
>> @PathVariable : for passing dynamic we need to pas the value
in path variable.
>> Logger : Logger is used to showcase the running status of the program
 as well as through an error is happens on which line
 */

import com.altimetrik.trainmicroservice.exception.TrainNumberNotExistsException;
import com.altimetrik.trainmicroservice.model.Train;
import com.altimetrik.trainmicroservice.service.TrainService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@Controller
@RequestMapping("/train-api")


public class TrainController
{
    @Autowired
    private TrainService trainService;
    private static final Logger logger = LoggerFactory.getLogger(TrainController.class);

    /*
       <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Add Train Details>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
     */
    @PostMapping(value = "/train")
    public Train addTrainDetail(@RequestBody  @Valid Train train)
    {
        System.out.println("Train Details Successfully Added !!");
        logger.info("Received a request to save train Detail : {}", train);
        return trainService.addTrainDetail(train);

    }

    /*
       <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Get TrainDetails by Number>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
     */
    @GetMapping(value = "/train/{id}")
    public Train getTrainByNumber(@PathVariable("id") int trainNumber) throws TrainNumberNotExistsException
    {
        logger.info("Received a request to get Train by Number : {}", trainNumber);
        return trainService.getTrainByNumber(trainNumber);
    }

    /*
       <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Update TrainDetails by Number>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    */
    @PutMapping(value = "/train")
    public Train updateTrainByNumber(@RequestBody Train train) throws TrainNumberNotExistsException
    {
        logger.info("Received a request to update Train Details: {}", train);
        return trainService.updateTrainByNumber(train);
    }
    /*
       <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Delete Train by Number>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
     */
    @DeleteMapping(value = "/train/{id}")
    public String deleteTrainByNumber(@PathVariable("id") int trainNumber) throws TrainNumberNotExistsException
    {
        logger.info("Received a request to Delete Train Details: {}", trainNumber);
        return trainService.deleteTrainByNumber(trainNumber);
    }
    /*
       <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Get AllTrain by Number>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
     */
    @GetMapping(value = "/train")
    public List<Train> getAllTrainDetails()
    {
        logger.info("Received a request to Get All Train Details: {}");
        return trainService.getAllTrainDetails();
    }


} // Last Bracket

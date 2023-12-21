package com.altimetrik.trainmicroservice.service;
import com.altimetrik.trainmicroservice.Controller.TrainController;
import com.altimetrik.trainmicroservice.exception.TrainNumberNotExistsException;
import com.altimetrik.trainmicroservice.model.Train;
import com.altimetrik.trainmicroservice.repository.TrainRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/*
>> @Service : annotation is primarily used to denote a class as a service component
in the Service layer of an application.This layer typically contains the business logic
 of the application, handling complex processing, calculations, data manipulation,
 and interaction with repositories or other services

 >> In this class we will write the whole implementation of the class including logic and functioning
    of the function.
 */

@Service
public class TrainServiceImplmentation implements TrainService {


    //Create a reference varibale of the Repository Interface or Dao class and make it autowired
    @Autowired
    private TrainRepository trainRepository;
    private static final Logger logger = LoggerFactory.getLogger(TrainController.class);

    /*
    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Add Train Details>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
     */
    @Override
    public Train addTrainDetail(Train train) {
        System.out.println("Train Detail Added Successfully !!");
        logger.info("Received a request to Add Train Details : {}", train);
        return trainRepository.save(train);
//        We use .save() method to store the Details Enterd By the user
//        inside the Database as per the column.
    }

    /*
    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Get TrainDetail by Train Number>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
     */
    @Override
    public Train getTrainByNumber(int trainNumber) throws TrainNumberNotExistsException
    {
//        Optional<Train> is a container from JAVA 8 which is used asa container
//        indicates that the variable can either hold an instance of a Train object
//        or be empty (contain no value).
            Optional<Train> train = trainRepository.findById(trainNumber);
            //Now Check whether it contain something or not
            if(train.isEmpty())
            {
                throw new TrainNumberNotExistsException("Train Number Does not Exisit !!! Try Again");
            }
            logger.info("Received a request to Get Train Details by Number: {}", trainNumber);
            return train.get();
    }


    /*
       <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Update TrainDetails by Train Number>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
     */
        @Override
    public Train updateTrainByNumber(Train train) throws TrainNumberNotExistsException
        {
            //Check whether the Train id Exist of not then if yes then user is able to update the details
            if(getTrainByNumber(train.getTrainNumber())!=null)
            {
                return trainRepository.save(train);
            }
            logger.info("Received a request to Update Train Details by Number: {}", train);
            return null;
            //If no Result found It will return null s
        }
    /*
    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Delete Train by Train Number>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
     */
    @Override
    public String deleteTrainByNumber(int trainNumber) throws TrainNumberNotExistsException {
        //Check kra lenge pele id available hai ya nahi fr delete krnege
        //ek Train ka object bna kr usme save kra lo
        String message =  "Product does not exist";
        Train train  = getTrainByNumber(trainNumber);
        if(train!=null)
        {
            // Agr is TrainNumber hoga to delete ho jaega otherwise ni hoga
            trainRepository.deleteById(trainNumber);
            message = "Train Deleted successfully !!";
            return message;
        }
        logger.info("Received a request to Delete Train Details by Number: {}", trainNumber);
        return message;
    }
     /*
    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Get allTrainDetail>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
     */
    @Override
    public List<Train> getAllTrainDetails()
    {
        logger.info("Received a request to Get All Train Details: {}");
        return trainRepository.findAll();
        // We need to call findAll() method to getAllTrainDetails() from the Database
    }


} // Last Bracket

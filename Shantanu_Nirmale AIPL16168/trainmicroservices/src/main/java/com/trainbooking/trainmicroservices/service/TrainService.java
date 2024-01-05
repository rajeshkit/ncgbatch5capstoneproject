package com.trainbooking.trainmicroservices.service;

import com.trainbooking.trainmicroservices.exception.TrainNumberNotExistException;
import com.trainbooking.trainmicroservices.model.Train;

import java.util.List;

public interface TrainService {
     Train addTrain(Train train);

     List<Train> getAllTrainDetails();

     Train getTrainByTrainNumber(int trainNumber) throws TrainNumberNotExistException;

     Train updateTrainDetails(Train train) throws TrainNumberNotExistException;

     String deleteTrainByTrainNumber(int trainNumber)throws TrainNumberNotExistException;
}

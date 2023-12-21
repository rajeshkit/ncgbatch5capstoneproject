package com.altimetrik.trainmicroservice.service;

import com.altimetrik.trainmicroservice.exception.TrainNumberAlreadyExists;
import com.altimetrik.trainmicroservice.exception.TrainNumberNotFoundException;
import com.altimetrik.trainmicroservice.model.Train;

import java.util.List;

public interface TrainService
{
    Train addTrain(Train train) throws TrainNumberAlreadyExists;
    List<Train> getAllTrain();
    Train getTrainByNumber(int trainNumber) throws TrainNumberNotFoundException;
    Train updateTrain(Train train) throws TrainNumberNotFoundException;
    String deleteTrainByNumber(int trainNumber) throws TrainNumberNotFoundException;
}

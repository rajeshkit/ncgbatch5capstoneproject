package com.altimetrik.trainmicroservices.service;


import com.altimetrik.trainmicroservices.exception.TrainNumberAlreadyExistsException;
import com.altimetrik.trainmicroservices.exception.TrainNumberNotExistsException;
import com.altimetrik.trainmicroservices.model.Train;

import java.util.List;

public interface TrainService {
    Train addTrain(Train train) throws TrainNumberAlreadyExistsException;

    List<Train> getAllTrains();

    Train getTrainByTrainNumber(long trainNumber) throws TrainNumberNotExistsException;

    Train updateTrain(Train train) throws TrainNumberNotExistsException;

    String deleteTrainByTrainNumber(long trainNumber) throws TrainNumberNotExistsException;
}

package com.altimetrik.trainmicroservice.service;

import com.altimetrik.trainmicroservice.exception.TrainNumberAlreadyExistsException;
import com.altimetrik.trainmicroservice.exception.TrainNumberNotExistsException;
import com.altimetrik.trainmicroservice.model.Train;

import java.util.List;

public interface TrainService {

    Train addTrain(Train train) throws TrainNumberAlreadyExistsException;

    List<Train> getAllTrains();

    Train getTrainByNumber(int trainNumber) throws TrainNumberNotExistsException;

    Train updateTrain(Train train) throws TrainNumberNotExistsException;

    String deleteTrainByNumber(int trainNumber) throws TrainNumberNotExistsException;
}
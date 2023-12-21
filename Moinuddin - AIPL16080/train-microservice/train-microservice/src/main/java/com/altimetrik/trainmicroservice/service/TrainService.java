package com.altimetrik.trainmicroservice.service;

import com.altimetrik.trainmicroservice.exceptions.TrainIdNotExistsException;
import com.altimetrik.trainmicroservice.model.Train;

import java.util.List;
import java.util.Optional;

public interface TrainService {

    Train addTrain(Train train);

    List<Train> getAllTrains();

    Train getTrainByNumber(String trainNumber) throws TrainIdNotExistsException;

    Train updateTrain(Train train) throws TrainIdNotExistsException;

    String deleteTrainByNumber(String trainNumber) throws TrainIdNotExistsException;
}

package com.train.trainmicroservice.service;

import com.train.trainmicroservice.exception.TrainIdNotExistException;
import com.train.trainmicroservice.model.Train;

import java.util.List;

public interface TrainService {

    Train addTrain(Train train);

    List<Train> getAllTrain();

    Train getTrainById(int trainNumber) throws TrainIdNotExistException;

    Train updateTrain(Train train) throws TrainIdNotExistException;

    String deleteTrainById(int trainId) throws TrainIdNotExistException;
}






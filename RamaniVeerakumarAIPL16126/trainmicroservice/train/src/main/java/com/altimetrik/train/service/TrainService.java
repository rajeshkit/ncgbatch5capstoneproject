package com.altimetrik.train.service;

import com.altimetrik.train.exception.TrainIdNotExistsException;
import com.altimetrik.train.model.Train;

import java.util.List;

public interface TrainService {
    public Train getTrainByNumber(int trainNumber) throws TrainIdNotExistsException;
    public Train addTrain(Train train);
    List<Train> getAllTrains();
    Train updateTrain(Train train) throws TrainIdNotExistsException;
    String deleteTrainByNumber(int trainNumber) throws TrainIdNotExistsException;
}

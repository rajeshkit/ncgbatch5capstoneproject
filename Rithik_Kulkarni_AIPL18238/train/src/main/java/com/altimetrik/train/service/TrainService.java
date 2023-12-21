package com.altimetrik.train.service;

import com.altimetrik.train.exception.TrainNumberNotExistException;
import com.altimetrik.train.model.Train;

import java.util.List;

public interface TrainService {

    public Train addTrain(Train train);

    public List<Train> getAllTrains();

    public Train getTrainByTrainNumber(int trainNumber) throws TrainNumberNotExistException;

    public Train updateTrain(Train Train) throws TrainNumberNotExistException;

    public String deleteTrainByNumber(int trainNumber) throws TrainNumberNotExistException;

}

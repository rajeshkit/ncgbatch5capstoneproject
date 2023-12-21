package com.altimetrik.trainbooking.service;

import com.altimetrik.trainbooking.exception.TrainNumberNotExistsException;
import com.altimetrik.trainbooking.model.Train;

import java.util.List;

public interface TrainService {
    Train addTrain(Train train);

    List<Train> getAlltrain();

    Train getTrainByNumber(int trainNumber) throws TrainNumberNotExistsException;

    Train updateTrain(Train train) throws TrainNumberNotExistsException;

    String deleteTrainByNumber(int trainNumber) throws TrainNumberNotExistsException;

}
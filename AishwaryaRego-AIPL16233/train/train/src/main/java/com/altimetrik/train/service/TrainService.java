package com.altimetrik.train.service;

import com.altimetrik.train.exception.TrainAlreadyExistsException;
import com.altimetrik.train.exception.TrainNotExistsException;
import com.altimetrik.train.model.Train;

import java.util.List;

public interface TrainService {
    List<Train> getAllTrains();
    Train getTrainByNum(int trainNum) throws TrainNotExistsException;
    Train addTrain(Train train) throws TrainAlreadyExistsException;
    Train updateTrain(Train train) throws TrainNotExistsException;
    String deleteTrain(int trainNum) throws TrainNotExistsException;
}

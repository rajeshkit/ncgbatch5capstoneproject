package com.altimetrik.trainbookingsystem.service;

import com.altimetrik.trainbookingsystem.exception.TrainNotExistsException;
import com.altimetrik.trainbookingsystem.model.Train;

import java.util.List;

public interface TrainService {
    Train addTrain(Train train);

    List<Train> getAlltrain();

    Train getTrainByNo(int trainNo) throws TrainNotExistsException;

    Train updateTrain(Train train) throws TrainNotExistsException;

    String deleteTrainByNo(int trainNo) throws TrainNotExistsException;


}

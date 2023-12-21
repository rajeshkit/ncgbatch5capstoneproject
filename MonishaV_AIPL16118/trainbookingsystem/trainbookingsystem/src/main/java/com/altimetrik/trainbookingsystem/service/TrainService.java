package com.altimetrik.trainbookingsystem.service;

import com.altimetrik.trainbookingsystem.exception.TrainNoNotExistsException;
import com.altimetrik.trainbookingsystem.model.Train;

import java.util.List;

public interface TrainService {
    Train addTrain(Train train);

    List<Train> getAllTrain();

    Train getTrainByNo(String trainNo) throws TrainNoNotExistsException;

    Train updateTrain(Train train) throws TrainNoNotExistsException;

    String deleteTrainByNo(String trainNo) throws TrainNoNotExistsException;


}

package com.Train.train.service;


import com.Train.train.exception.TrainNumberNotFound;
import com.Train.train.model.Train;

import java.util.List;

public interface TrainService {
    Train addTrain(Train train);

    List<Train> getAllTrains();

    Train getTrainByTrainNumber(int trainNumber) ;
    Train updateTrain(Train train) ;

    String deleteTrainByTrainNumber(int trainNumber) throws TrainNumberNotFound;
}

package com.train.trainmicroservice.service;

import com.train.trainmicroservice.model.Train;

import java.util.List;

public interface TrainService {
    Train addTrain(Train train);

    List<Train> getAllTrains();

    Train getTrainByTrainNo(int trainNumber);

    Train updateTrain(Train train);

    String deleteTrainByTrainNo(int trainNumber);


}

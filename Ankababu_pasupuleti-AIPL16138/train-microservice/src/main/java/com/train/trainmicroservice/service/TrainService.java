package com.train.trainmicroservice.service;

import com.train.trainmicroservice.model.Train;

import java.util.List;

public interface TrainService {
    public Train addTrain(Train train);
    public List<Train> getAllTrains();
    public Train getTrainByTrainNo(int trainNumber);
    public Train updateTrain(Train train);
    public void deleteTrainByTrainNo(int trainNumber);


}

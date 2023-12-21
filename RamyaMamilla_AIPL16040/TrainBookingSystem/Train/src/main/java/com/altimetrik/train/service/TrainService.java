package com.altimetrik.train.service;

import com.altimetrik.train.model.Train;

import java.util.List;

public interface TrainService {
    public Train addTrain(Train train);
    public List<Train> viewAllTrains();
    public Train getTrainById(int trainId);
    public Train updateTrain(Train train);
    public String deleteTrainById(int trainId);
}

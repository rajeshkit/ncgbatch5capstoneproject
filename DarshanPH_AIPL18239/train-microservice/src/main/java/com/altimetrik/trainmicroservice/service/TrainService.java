package com.altimetrik.trainmicroservice.service;

import com.altimetrik.trainmicroservice.exception.TrainIdNotExistsException;
import com.altimetrik.trainmicroservice.model.Train;

import java.util.List;

public interface TrainService {
    public Train addTrain(Train train);
    public List<Train> getAllTrain();
    public Train getTrainById(int trainId)throws TrainIdNotExistsException;
    public Train updateTrain(Train train)throws TrainIdNotExistsException, TrainIdNotExistsException;
    public String deleteTrainById(int trainId)throws TrainIdNotExistsException;
}

package com.altimetrikfinalproject.trainmicroservice.service;

import com.altimetrikfinalproject.trainmicroservice.exception.TrainDoesNotExistException;
import com.altimetrikfinalproject.trainmicroservice.model.Train;

import java.util.List;
import java.util.Optional;

public interface TrainService {
    public Train addTrain(Train train);
    public List<Train> getAllTrains();
    public Optional<Optional> getTrainByID(int trainNumber) throws TrainDoesNotExistException;
    public Train updateTrain(Train train) throws TrainDoesNotExistException;
    public String deleteTrain(int trainNumber) throws TrainDoesNotExistException;
}

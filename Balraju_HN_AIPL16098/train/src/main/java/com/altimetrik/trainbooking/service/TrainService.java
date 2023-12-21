package com.altimetrik.trainbooking.service;

import com.altimetrik.trainbooking.exception.NoSuchElementException;
import com.altimetrik.trainbooking.modle.Train;

import java.util.List;

import java.util.Optional;

public interface TrainService {
    public Train addTrain(Train train);
    public List<Train> getAllTrain();
    public Train getTrainByNumber(int tainNumber)throws NoSuchElementException;
    public Train updateTrain(Train train)throws  NoSuchElementException;
    public String deleteTrainByNumber(int tainNumber) throws NoSuchElementException;

}

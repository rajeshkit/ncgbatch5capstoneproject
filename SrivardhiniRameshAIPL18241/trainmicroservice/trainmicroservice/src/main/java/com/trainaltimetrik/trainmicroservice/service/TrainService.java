package com.trainaltimetrik.trainmicroservice.service;

import com.trainaltimetrik.trainmicroservice.exception.TrainNumberAlreadyExistException;
import com.trainaltimetrik.trainmicroservice.exception.TrainNumberNotExistException;
import com.trainaltimetrik.trainmicroservice.model.Train;

import java.util.List;


public interface TrainService {
    Train addTrain(Train train) throws TrainNumberNotExistException, TrainNumberAlreadyExistException;
	List<Train> getAllTrain();
	Train getTrainByNumber(int trainNumber)throws TrainNumberNotExistException;
	Train updateTrain(Train train) throws TrainNumberNotExistException;
	String deleteTrainByNumber(int trainNumber) throws TrainNumberNotExistException;
}

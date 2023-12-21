package com.altimetrik.Train.service;

import java.util.List;
import com.altimetrik.Train.exception.TrainIdNotExistsException;
import com.altimetrik.Train.model.Train;


public interface TrainService {



        Train addTrain(Train train);
        List<Train> getAllTrains();
        Train getTrainById(int trainId) throws TrainIdNotExistsException;
        Train updateTrain(Train train) throws TrainIdNotExistsException;
        String deleteTrainById(int trainId) throws TrainIdNotExistsException;
    }



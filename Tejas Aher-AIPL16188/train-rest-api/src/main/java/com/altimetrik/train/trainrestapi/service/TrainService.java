package com.altimetrik.train.trainrestapi.service;

import com.altimetrik.train.trainrestapi.exception.TrainIDNotExistsException;
import com.altimetrik.train.trainrestapi.model.Train;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TrainService {

    public Train addTrain(@RequestBody Train train);

    public List<Train> getAllTrains();

    public Train getTrainByTrainID(String trainID) throws TrainIDNotExistsException;

    public Train updateTrain(Train train, String trainId) throws TrainIDNotExistsException;

    public String deleteTrainByTrainID(String trainId) throws TrainIDNotExistsException;
}

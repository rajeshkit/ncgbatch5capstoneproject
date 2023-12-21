package com.railways.train.service;

import com.railways.train.exception.TrainWithThatNumberExists;
import com.railways.train.exception.TrainNumberNotFoundException;
import com.railways.train.model.Train;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TrainService {
    public Train addTrain(Train train) throws TrainWithThatNumberExists;

    public List<Train> getAllTrains();

    public Train getTrainById(long trainNumber) throws TrainNumberNotFoundException;

    public Train updateTrain(Train train) throws TrainNumberNotFoundException;

    public String deleteTrainById(long trainNumber) throws TrainNumberNotFoundException;
}

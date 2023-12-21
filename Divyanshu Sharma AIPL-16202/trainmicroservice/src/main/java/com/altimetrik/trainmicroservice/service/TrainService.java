package com.altimetrik.trainmicroservice.service;
import com.altimetrik.trainmicroservice.exception.TrainNumberNotExistsException;
import com.altimetrik.trainmicroservice.model.Train;

import java.util.List;


public interface TrainService {
    public Train addTrainDetail(Train train);

    public Train getTrainByNumber(int trainNumber) throws TrainNumberNotExistsException;

    public Train updateTrainByNumber(Train train) throws TrainNumberNotExistsException;

    public String deleteTrainByNumber(int trainNumber) throws TrainNumberNotExistsException;

    public List<Train> getAllTrainDetails();
}

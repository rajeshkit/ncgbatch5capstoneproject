package com.trainmicroservice.service;

import com.trainmicroservice.exception.TrainNumberDoesNotExistException;
import com.trainmicroservice.model.Train;
import java.util.List;

public interface TrainService {
    Train saveTrainDetails(Train train);
    List<Train> getAllTrainsDetail();
    Train getTrainDetailsById(int trainNumber) throws TrainNumberDoesNotExistException;
    Train updateTrainDetails(Train train) throws TrainNumberDoesNotExistException;
    String deleteTrainDetails(int trainNumber) throws TrainNumberDoesNotExistException;
}

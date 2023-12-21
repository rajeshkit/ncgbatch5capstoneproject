package com.railwaybooking.Train.service;

import com.railwaybooking.Train.exception.TrainNumberNotExistException;
import com.railwaybooking.Train.model.TrainInfo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TrainService {
    TrainInfo addTrain(TrainInfo trainInfo);

    List<TrainInfo> getAllTrains();

    TrainInfo getTrainByNumber(long trainNumber) throws TrainNumberNotExistException;

    TrainInfo updateTrainInfo(TrainInfo trainInfo) throws TrainNumberNotExistException;


    String deleteTrainByNumber(long trainNumber) throws TrainNumberNotExistException;
}

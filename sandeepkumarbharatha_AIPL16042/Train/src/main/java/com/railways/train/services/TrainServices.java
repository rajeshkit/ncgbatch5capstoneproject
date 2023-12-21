package com.railways.train.services;

import com.railways.train.exceptions.TrainNumberNotFound;
import com.railways.train.model.Train;

import java.util.List;

public interface TrainServices {

    Train addTrainDetails(Train train);

    Train getTrainByNumber(long trainNumToSearch) throws TrainNumberNotFound;

    List getAllDetails() ;

    Train updateTainDetails(Train train) throws TrainNumberNotFound;

    String deleteByTrainNumber(Long trainNumberToDelete) throws TrainNumberNotFound;
}

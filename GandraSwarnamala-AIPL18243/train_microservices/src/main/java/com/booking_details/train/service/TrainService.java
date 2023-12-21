package com.booking_details.train.service;

import com.booking_details.train.exception.TrainIdNotFoundException;
import com.booking_details.train.model.TrainModel;

import java.util.List;

public interface TrainService {
    TrainModel addTrainDetails(TrainModel trainModel);

    List<TrainModel> getAllTrainDetails();

    TrainModel getAllTrainDetailsById(Long trainNumber) throws TrainIdNotFoundException;

    TrainModel updateTrainDetails(TrainModel trainModel) throws TrainIdNotFoundException;

    String deleteTrainDetailsById(Long trainNumber) throws TrainIdNotFoundException;
}

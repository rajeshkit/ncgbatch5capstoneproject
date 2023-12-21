package com.Booking.train.services;

import com.Booking.train.customexception.TrainIdNotFoundException;
import com.Booking.train.model.TrainResources;

import java.util.List;

public interface TrainService {
    TrainResources addTrain(TrainResources trainResources);

    TrainResources getTrainById(Long trainId) throws TrainIdNotFoundException;

   List<TrainResources> getAllTrainDetail();

    TrainResources updateTrain(TrainResources trainResources) throws TrainIdNotFoundException;

    String deleteTrainById(Long trainId) throws TrainIdNotFoundException;
}

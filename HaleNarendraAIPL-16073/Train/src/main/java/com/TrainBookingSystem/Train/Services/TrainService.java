package com.TrainBookingSystem.Train.Services;



import com.TrainBookingSystem.Train.Exception.TrainIdNotFoundException;
import com.TrainBookingSystem.Train.model.TrainResources;

import java.util.List;

public interface TrainService {

    TrainResources addTrainResources(TrainResources trainResources);

    List<TrainResources> getAllTrainDetail();

    TrainResources getTrainById(Long trainId) throws TrainIdNotFoundException;

    TrainResources updateTrainDetail(TrainResources trainResources) throws TrainIdNotFoundException;

    String deleteTrainById(Long productId) throws TrainIdNotFoundException;
}

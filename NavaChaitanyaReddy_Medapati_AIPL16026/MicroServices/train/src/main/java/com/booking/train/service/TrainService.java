package com.booking.train.service;

import com.booking.train.exception.TrainNumberNotExistsException;
import com.booking.train.model.TrainResources;

import java.util.List;

public interface TrainService {
    TrainResources addTrainResources(TrainResources trainResources);

    List<TrainResources> getAllTrainResources();

    TrainResources getAllTrainResourcesById(Long trainNumber) throws TrainNumberNotExistsException;

    TrainResources updateTrainResource(TrainResources trainResources) throws TrainNumberNotExistsException;

    String deleteTrainResourceByTrainNumber(Long trainNumber) throws TrainNumberNotExistsException;
}

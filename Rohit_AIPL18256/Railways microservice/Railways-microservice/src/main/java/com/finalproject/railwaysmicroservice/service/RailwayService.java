package com.finalproject.railwaysmicroservice.service;

import com.finalproject.railwaysmicroservice.exception.TrainIsNotExistHereException;
import com.finalproject.railwaysmicroservice.model.Railway;

import java.util.List;
import java.util.Optional;

public interface RailwayService {

    public Railway addTrain(Railway railway);

    public List<Railway> getAllTrains();

    public Optional<Optional> getTrainById(int trainNumber) throws TrainIsNotExistHereException;

    public Railway updateTrain(Railway railway) throws TrainIsNotExistHereException;

    public String deleteTrain(int trainNumber) throws TrainIsNotExistHereException;

}

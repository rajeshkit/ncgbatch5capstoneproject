package com.project.Railways.service;

import com.project.Railways.exception.TrainNotFoundException;
import com.project.Railways.model.Railway;

import java.util.List;
import java.util.Optional;

public interface RailwayService {

    public Railway addTrain(Railway railway);

    public List<Railway> getAllTrains();

    public Optional<Optional> getTrainById(int trainNumber) throws TrainNotFoundException;

    public Railway updateTrain(Railway railway) throws TrainNotFoundException;

    public String deleteTrain(int trainNumber) throws TrainNotFoundException;

}


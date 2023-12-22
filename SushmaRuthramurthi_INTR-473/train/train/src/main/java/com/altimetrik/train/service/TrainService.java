package com.altimetrik.train.service;

import com.altimetrik.train.dto.TrainCoachResponse;
import com.altimetrik.train.dto.TrainResponse;
import com.altimetrik.train.entity.Train;
import com.altimetrik.train.entity.TrainCoaches;
import com.altimetrik.train.exception.TrainNotExistsException;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TrainService {
    public TrainResponse addTrain(Train train);
    public List<TrainCoachResponse> addTrainCoaches(int trainNumber, TrainCoaches trainCoaches);
    public TrainResponse getTrainDetials(int trainNumber,boolean includecoaches);
    public TrainResponse updateTrain(@RequestBody Train train) throws TrainNotExistsException;
    public String deletaTrain(int trainNumber) throws TrainNotExistsException;

}

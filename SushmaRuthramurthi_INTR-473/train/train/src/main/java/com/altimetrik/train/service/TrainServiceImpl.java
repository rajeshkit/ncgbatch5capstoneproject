package com.altimetrik.train.service;

import com.altimetrik.train.TrainApplication;
import com.altimetrik.train.dto.TrainCoachResponse;
import com.altimetrik.train.dto.TrainResponse;
import com.altimetrik.train.entity.Train;
import com.altimetrik.train.entity.TrainCoaches;
import com.altimetrik.train.exception.TrainNotExistsException;
import com.altimetrik.train.repository.TrainCoachesRepository;
import com.altimetrik.train.repository.TrainRepository;
//import lombok.extern.slf4j.XSlf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {
  //  private static final Logger logger = LoggerFactory.getLogger(TrainServiceImpl.class);
    private TrainRepository trainRepository;
    private TrainCoachesRepository trainCoachesRepository;

    @Autowired
    public TrainServiceImpl(TrainRepository trainRepository, TrainCoachesRepository trainCoachesRepository) {
        this.trainRepository = trainRepository;
        this.trainCoachesRepository = trainCoachesRepository;
    }

    @Override
    public TrainResponse addTrain(Train train) {
        Train train1 = trainRepository.save(train);
        TrainResponse trainResponse = new TrainResponse();
        trainResponse.setTrainNumber(train1.getTrainNumber());
        trainResponse.setTrainName(train1.getTrainName());
        trainResponse.setTrainCoaches(new ArrayList<>());
        return trainResponse;
    }

    @Override
    public List<TrainCoachResponse> addTrainCoaches(int trainNumber, TrainCoaches trainCoaches) {
        TrainCoaches trainCoaches1 = trainCoachesRepository.save(trainCoaches);
        List<TrainCoachResponse> list = new ArrayList<>();
        TrainCoachResponse trainCoachResponse = new TrainCoachResponse();
        trainCoachResponse.setId(trainCoaches1.getId());
        trainCoachResponse.setCoachsize(trainCoaches.getCoachsize());
        trainCoachResponse.setCoachtypeid(trainCoaches.getCoachtypeid());
        list.add(trainCoachResponse);
        return list;
    }

    @Override
    public TrainResponse getTrainDetials(int trainNumber, boolean includecoaches) {
    //    logger.info("Getting train details for Train Number:"+trainNumber);
        if (includecoaches == false) {
            Train train1 = trainRepository.findById(trainNumber).get();
            TrainResponse trainResponse = new TrainResponse();
            trainResponse.setTrainNumber(train1.getTrainNumber());
            trainResponse.setTrainName(train1.getTrainName());
            trainResponse.setTrainCoaches(new ArrayList<>());
            return trainResponse;
        } else {

            List<TrainCoaches> trainCoaches1 = trainCoachesRepository.findBytrainnumber(trainNumber);
            List<TrainCoachResponse> list = new ArrayList<>();
            trainCoaches1.forEach((s) -> {
                TrainCoachResponse trainCoachResponse = new TrainCoachResponse();
                trainCoachResponse.setId(s.getId());
                trainCoachResponse.setCoachtypeid(s.getCoachtypeid());
                trainCoachResponse.setCoachsize(s.getCoachsize());
                list.add(trainCoachResponse);
            });

            Train train1 = trainRepository.findById(trainNumber).get();
            TrainResponse trainResponse = new TrainResponse();
            trainResponse.setTrainNumber(train1.getTrainNumber());
            trainResponse.setTrainName(train1.getTrainName());
            trainResponse.setTrainCoaches(list);
            return trainResponse;
        }

    }

    @Override
    public TrainResponse updateTrain(Train train) throws TrainNotExistsException {

        if (trainRepository.findBytrainNumber(train.getTrainNumber())!=null) {
            Train train1 = trainRepository.save(train);
            TrainResponse trainResponse = new TrainResponse();
            trainResponse.setTrainNumber(train1.getTrainNumber());
            trainResponse.setTrainName(train1.getTrainName());
            trainResponse.setTrainCoaches(new ArrayList<>());
            return trainResponse;
        }
        throw new TrainNotExistsException("Train Number is not Exit!!! Please enter valid Train Number");
    }

    @Override
    public String deletaTrain(int trainNumber) throws TrainNotExistsException {
       if (trainRepository.findBytrainNumber(trainNumber)!=null){
           trainRepository.deleteById(trainNumber);
           return "Deleted successfully";
       }else{
           throw new TrainNotExistsException("Train Number is not Exit!!! please enter valid number to delete");
       }
    }
}

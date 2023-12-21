package com.train.trainmicroservice.service;

import com.train.trainmicroservice.exception.TrainNumberAlreadyExistsException;
import com.train.trainmicroservice.exception.TrainNumberNotExistsException;
import com.train.trainmicroservice.model.Train;
import com.train.trainmicroservice.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.annotation.Target;
import java.util.List;
import java.util.Optional;

@Service
public class TrainServiceImpl implements TrainService{

    private final TrainRepository trainRepository;

    @Autowired
    public TrainServiceImpl(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    @Override
    public Train addTrain(Train train) {
        List<Train> trainList=trainRepository.findAll();
        if(trainList.contains(train)){
            throw new TrainNumberAlreadyExistsException("Train Number Already Exist");
        }
        return trainRepository.save(train);

    }

    @Override
    public List<Train> getAllTrains() {
        return  trainRepository.findAll();
    }

    @Override
    public Train getTrainByTrainNo(int trainNumber) {
       Optional<Train> train=trainRepository.findById(trainNumber);
       if(train.isEmpty()){
           throw new TrainNumberNotExistsException("Train Number Not Exist");
       }
           return train.get();
    }

    @Override
    public Train updateTrain(Train train) {
        if(getTrainByTrainNo(train.getTrainNumber())!=null) {
            return trainRepository.save(train);
        }
        else {
            throw new TrainNumberNotExistsException("No Train number exists");
        }
    }

    @Override
    public String deleteTrainByTrainNo(int trainNumber) {
        Train train=getTrainByTrainNo(trainNumber);
        if(train!=null){
            trainRepository.deleteById(trainNumber);
            return "Train Deleted Successfully";
        }
        else {
            throw new TrainNumberNotExistsException("Train Number Not Exist");
        }
    }

}

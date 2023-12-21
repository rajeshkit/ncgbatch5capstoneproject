package com.altimetrik.train.service;

import com.altimetrik.train.exception.TrainNumberNotFoundException;
import com.altimetrik.train.model.Train;
import com.altimetrik.train.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TrainServiceImpl implements TrainService{

    private TrainRepository trainRepository;

    @Autowired
    public TrainServiceImpl(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    @Override
    public Train addTrain(Train train) {
        return trainRepository.save(train);
    }

    @Override
    public List<Train> viewAllTrains() {
        return trainRepository.findAll();
    }

    @Override
    public Train getTrainById(int trainId){
        Optional<Train> t1=trainRepository.findById(trainId);
        if (t1.isEmpty()){
            throw new TrainNumberNotFoundException("Train Number Not Found!");
        }
        return t1.get();
    }

    @Override
    public Train updateTrain(Train train) {
        if (getTrainById(train.getTrainNo())!=null){
            return trainRepository.save(train);
        }
        else {
            throw new TrainNumberNotFoundException("Train Number Not Found!! Search for another Train!");
        }
    }

    @Override
    public String deleteTrainById(int trainId){
        if (getTrainById(trainId)!=null){
            trainRepository.deleteById(trainId);
            return "Train details Deleted Successfully for Train: " +trainId;
        }
        else {
            throw new TrainNumberNotFoundException("Train Number not found! Try for another Train!");
        }
    }
}

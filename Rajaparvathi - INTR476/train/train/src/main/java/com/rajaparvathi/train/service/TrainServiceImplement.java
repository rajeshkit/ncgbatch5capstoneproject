package com.rajaparvathi.train.service;

import com.rajaparvathi.train.model.Train;
import com.rajaparvathi.train.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainServiceImplement implements TrainService{
    @Autowired
    private TrainRepository trainRepository;

    @Override
    public Train addTrainDetails(Train train) {
        return trainRepository.save(train);
    }

    @Override
    public List<Train> getAllTrainDetails() {
        return trainRepository.findAll();
    }

    @Override
    public Train searchTrainByNumber(int trainNumber) {
        Optional<Train> t = trainRepository.findById(trainNumber);
        return t.get();
    }

    @Override
    public Train updateDetails(Train train) {
        if(searchTrainByNumber(Train.getTrainNumber())!=null){
            return trainRepository.save(train);
        }
        return null;
    }

    @Override
    public String removeTrainByNumber(int trainNumber) {
        String message = "No such train found";
        Train t = searchTrainByNumber(trainNumber);
        if(t!=null){
            trainRepository.deleteById(trainNumber);
            message = "Train Details Deleted Successfully";
            return message;
        }
        return message;
    }
}

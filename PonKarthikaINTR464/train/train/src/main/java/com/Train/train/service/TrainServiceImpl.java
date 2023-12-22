package com.Train.train.service;


import com.Train.train.exception.TrainNumberNotFound;
import com.Train.train.model.Train;
import com.Train.train.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {
    @Autowired
    private TrainRepository trainRepository;


    @Override
    public Train addTrain(Train train) {
        return trainRepository.save(train);
    }

    @Override
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    @Override
    public Train getTrainByTrainNumber(int trainNumber) {

        return trainRepository.findTrainByTrainNumber(trainNumber);
    }

    @Override
    public Train updateTrain(Train train) {

            if(getTrainByTrainNumber(train.getTrainNumber())!=null) {
                return trainRepository.save(train);
            }
            return null;
    }

    @Override
    @Transactional
    public String deleteTrainByTrainNumber(int trainNumber) throws TrainNumberNotFound {
          String message="couldn't delete the train";
            Train tra=getTrainByTrainNumber(trainNumber);
            if(tra!=null){
                trainRepository.deleteTrainByTrainNumber(trainNumber);
                message="Train deleted successfully";
                return message;
            }
            return message;
        }
    }


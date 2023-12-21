package com.altimetrik.Train.service;

import com.altimetrik.Train.exception.TrainIdNotExistsException;
import com.altimetrik.Train.model.Train;
import com.altimetrik.Train.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public  class TrainServiceImpl implements TrainService {


    @Autowired
    private TrainRepository trainRepository;

    @Override
    public Train addTrain(Train t) {
        return trainRepository.save(t);
    }

    @Override
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    @Override
    public Train getTrainById(int trainId) throws TrainIdNotExistsException {
        Optional<Train> pro=trainRepository.findById(trainId);

        if(pro.isEmpty()){
            throw new TrainIdNotExistsException("TRAIN does not exists in the db!!check the Id");
        }
        return pro.get();
    }

    @Override
    public Train updateTrain(Train train) throws TrainIdNotExistsException {
        if(getTrainById(train.getTrainId())!=null) {
            return trainRepository.save(train);
        }
        return null;
    }

    public String deleteTrainById(int trainId) throws TrainIdNotExistsException {
        String message="Train does not exists to delete";
        if(getTrainById(trainId)!=null) {
            trainRepository.deleteById(trainId);
            message="Train deleted successfully";
            return message;
        }
        return message;
    }
}
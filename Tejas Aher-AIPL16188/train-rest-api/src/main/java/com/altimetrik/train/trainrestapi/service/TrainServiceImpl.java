package com.altimetrik.train.trainrestapi.service;

import com.altimetrik.train.trainrestapi.exception.TrainIDNotExistsException;
import com.altimetrik.train.trainrestapi.model.Train;
import com.altimetrik.train.trainrestapi.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Train getTrainByTrainID(String trainId) throws TrainIDNotExistsException {
        Optional<Train> optionalTrain = trainRepository.findById(trainId);
        optionalTrain.orElseThrow(() -> new TrainIDNotExistsException("Train ID not exists: " + trainId));
        if(optionalTrain.isPresent()){
            return optionalTrain.get();
        }

        return null;

    }

    @Override
    public Train updateTrain(Train train, String trainId) throws TrainIDNotExistsException {
        Train train1 = getTrainByTrainID(trainId);
        if (train1 == null) {
            throw new TrainIDNotExistsException("Train ID not exists: " + trainId);
        } else {
            return trainRepository.save(train);
        }

    }

    @Override
    public String deleteTrainByTrainID(String trainId) throws TrainIDNotExistsException {
        if (getTrainByTrainID(trainId) == null) {
            return "Train Id doesn't exists";
        } else {
            trainRepository.deleteById(trainId);
            return "Train with number " + trainId + " deleted successfully";
        }
    }
}

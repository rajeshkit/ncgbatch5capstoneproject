package com.railways.train.service;

import com.railways.train.exception.TrainWithThatNumberExists;
import com.railways.train.exception.TrainNumberNotFoundException;
import com.railways.train.model.Train;
import com.railways.train.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainServiceImpl implements TrainService {

    private TrainRepository trainRepository;
    @Autowired
    public TrainServiceImpl(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    @Override
    public Train addTrain(Train train) throws TrainWithThatNumberExists {
        Optional<Train> t=trainRepository.findById(train.getTrainNumber());
        if(t.isEmpty())
        {
            return trainRepository.save(train);
        }
        else {
            throw new TrainWithThatNumberExists("Train with that number exists!Try to add another.");
        }
    }

    @Override
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    @Override
    public Train getTrainById(long trainNumber) throws TrainNumberNotFoundException {
        Optional<Train> train = trainRepository.findById(trainNumber);
        if (train.isEmpty()) {
            throw new TrainNumberNotFoundException("Train Not Found");
        }
        return train.get();
    }

    @Override
    public Train updateTrain(Train train) throws TrainNumberNotFoundException {
        if (getTrainById(train.getTrainNumber()) != null) {
            return trainRepository.save(train);
        }
        return null;
    }

    @Override
    public String deleteTrainById(long trainNumber) throws TrainNumberNotFoundException {
        Train train = getTrainById(trainNumber);
        if (train != null) {
            trainRepository.deleteById(trainNumber);
            return "Train Deleted Successfully";
        }
        return null;
    }
}

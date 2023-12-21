package com.altimetrikfinalproject.trainmicroservice.service;

import com.altimetrikfinalproject.trainmicroservice.exception.TrainDoesNotExistException;
import com.altimetrikfinalproject.trainmicroservice.model.Train;
import com.altimetrikfinalproject.trainmicroservice.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainServiceImplementation implements TrainService{
    @Autowired
    private TrainRepository trainRepository;
    @Override
    public Train addTrain(Train train) {
        trainRepository.save(train);
        return train;
    }

    @Override
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    @Override
    public Optional<Optional> getTrainByID(int trainNumber) throws TrainDoesNotExistException {
        Optional train1 = trainRepository.findById(trainNumber);
        if(train1.isEmpty()){
            throw new TrainDoesNotExistException("The Trian Does not Exist", "The id is invalid");
        }
        return Optional.of(train1);
    }

    @Override
    public Train updateTrain(Train train) throws TrainDoesNotExistException {
        Optional<Train> train1 = trainRepository.findById(train.getTrainNumber());
        Train train2 =null;
        if(train1.isEmpty()){
            throw new TrainDoesNotExistException("Train Does not exist","The id is invalid");
        }
        train2 = train1.get();
        train2.setTrainName(train.getTrainName());
        train2.setTotalKms(train.getTotalKms());
        train2.setAcCoaches(train.getAcCoaches());
        train2.setTotalAcCoachSeats(train.getTotalAcCoachSeats());
        train2.setSleeperCoaches(train.getSleeperCoaches());
        train2.setSleeperCoachesTotalSeats(train.getSleeperCoachesTotalSeats());
        train2.setGeneralCoaches(train.getGeneralCoaches());
        train2.setGeneralCoachesTotalSeats(train.getGeneralCoachesTotalSeats());
        trainRepository.save(train2);
        return train2;
    }

    @Override
    public String deleteTrain(int trainNumber) throws TrainDoesNotExistException {
        Optional<Optional> t = getTrainByID(trainNumber);
        if(t!=null) {
            trainRepository.deleteById(trainNumber);
        }
        return null;
    }
}

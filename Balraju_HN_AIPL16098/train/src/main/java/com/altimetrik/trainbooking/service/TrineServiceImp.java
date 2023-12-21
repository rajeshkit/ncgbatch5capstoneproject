package com.altimetrik.trainbooking.service;

import com.altimetrik.trainbooking.Repository.TrainRepository;
import com.altimetrik.trainbooking.exception.NoSuchElementException;
import com.altimetrik.trainbooking.modle.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;
@Service
public class TrineServiceImp implements TrainService{
    @Autowired
    private TrainRepository trainRepository;
    @Override
    public Train addTrain(Train train) {
        return trainRepository.save(train);
    }

    @Override
    public List<Train> getAllTrain() {
        return trainRepository.findAll();
    }

    @Override
    public Train getTrainByNumber(int trainNumber) throws NoSuchElementException {
        Optional<Train> trai=trainRepository.findById(trainNumber);
        if (trai.isEmpty()){
            throw new NoSuchElementException("train number is not found in DB!!! CHICK PRODUCT id");
        }
        return trai.get();
    }

    @Override
    public Train updateTrain(Train train) throws NoSuchElementException{
        if(getTrainByNumber(train.getTrainNumber())!=null){

        return trainRepository.save(train);
        }
        return null;
    }

    @Override
    public String  deleteTrainByNumber(int trainNumber) throws NoSuchElementException{
        String message="train number does not exists to delete";
        Train t=getTrainByNumber(trainNumber);
        if(t!=null){
            trainRepository.deleteById(trainNumber);
            message="deleted succesfully";
            return  message;

        }
        return message;
    }
}

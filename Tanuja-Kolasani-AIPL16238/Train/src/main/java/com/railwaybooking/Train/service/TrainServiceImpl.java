package com.railwaybooking.Train.service;

import com.railwaybooking.Train.exception.TrainNumberNotExistException;
import com.railwaybooking.Train.model.TrainInfo;
import com.railwaybooking.Train.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainServiceImpl implements TrainService{
    @Autowired
    private TrainRepository trainRepository;
    @Override
    public TrainInfo addTrain(TrainInfo trainInfo) {
        return trainRepository.save(trainInfo);
    }

    @Override
    public List<TrainInfo> getAllTrains() {
        return trainRepository.findAll();
    }

    @Override
    public TrainInfo getTrainByNumber(long trainNumber) throws TrainNumberNotExistException {
        Optional<TrainInfo> t=trainRepository.findById(trainNumber);
        if(t.isEmpty()){
            throw new TrainNumberNotExistException("Train number not found Exception");
        }
        return t.get();
    }

    @Override
    public TrainInfo updateTrainInfo(TrainInfo trainInfo) throws TrainNumberNotExistException {
        if(getTrainByNumber(trainInfo.getTrainNumber())!=null){
            return trainRepository.save(trainInfo);
        }
        return null;
    }

    @Override
    public String deleteTrainByNumber(long trainNumber) throws TrainNumberNotExistException {
        String message="Train does not exists";
        TrainInfo trainInfo=getTrainByNumber(trainNumber);
        if(trainInfo!=null){
            trainRepository.deleteById(trainNumber);
            message="train deleted successfully";
            return message;
        }
        return message;
    }


}

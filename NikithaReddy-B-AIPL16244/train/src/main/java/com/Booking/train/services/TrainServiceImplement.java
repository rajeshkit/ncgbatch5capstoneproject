package com.Booking.train.services;

import com.Booking.train.customexception.TrainIdNotFoundException;
import com.Booking.train.model.TrainResources;
import com.Booking.train.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainServiceImplement implements TrainService {
    @Autowired
    private TrainRepository trainRepository;

    @Override
    public TrainResources addTrain(TrainResources trainResources) {

        return trainRepository.save(trainResources);
    }

    @Override
    public List<TrainResources> getAllTrainDetail() {
        return trainRepository.findAll();
    }

    @Override
    public TrainResources updateTrain(TrainResources trainResources) throws TrainIdNotFoundException {
        if (getTrainById(trainResources.getTrainNumber()) != null) {
            return trainRepository.save(trainResources);
        }
        return null;
    }

    @Override
    public TrainResources getTrainById(Long trainId) throws TrainIdNotFoundException {
        Optional<TrainResources> pro = trainRepository.findById(trainId);
        if (pro.isEmpty()) {
            throw new TrainIdNotFoundException("Train Id not found Exception!!!");
        }
        return pro.get();
    }
    public String deleteTrainById(Long trainId) throws TrainIdNotFoundException {
        String message="Train detail Does not exists to delete";
        TrainResources p=getTrainById(trainId);
        if(p!=null){
            trainRepository.deleteById(trainId);
            message="Train detail deleted successfully";
            return message;
        }
        return message;
    }


}









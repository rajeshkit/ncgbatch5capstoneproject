package com.finalproject.railwaysmicroservice.service;

import com.finalproject.railwaysmicroservice.exception.TrainIsNotExistHereException;
import com.finalproject.railwaysmicroservice.model.Railway;
import com.finalproject.railwaysmicroservice.repository.RailwaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RailwayServiceImplementation implements RailwayService {


    @Autowired
    private RailwaysRepository railwaysRepository;



    @Override
    public Railway addTrain(Railway railway) {
        railwaysRepository.save(railway);
        return railway;
    }




    @Override
    public List<Railway> getAllTrains() {

        return railwaysRepository.findAll();
    }




    @Override
    public Optional<Optional> getTrainById(int trainNumber) throws TrainIsNotExistHereException {
        Optional trainno1 = railwaysRepository.findById(trainNumber);
        if (trainno1.isEmpty()){
            throw new TrainIsNotExistHereException("Train is not exist ", "This Train Id is Invalid");
        }
        return Optional.of(trainno1);
    }




    @Override
    public Railway updateTrain(Railway railway) throws TrainIsNotExistHereException {
        Optional<Railway> trainno1 = railwaysRepository.findById(railway.getTrainNumber());
        Railway trainno2 = null ;
        if (trainno1.isEmpty()){
            //
        }
        trainno2 = trainno1.get();


        trainno2.setTrainName(railway.getTrainName());
        trainno2.setTotalKms(railway.getTotalKms());
        trainno2.setAcCoaches(railway.getAcCoaches());
        trainno2.setAcCoachesTotalSeats(railway.getAcCoachesTotalSeats());
        trainno2.setSleeperCoaches(railway.getSleeperCoaches());
        trainno2.setSleeperCoachesTotalSeats(railway.getSleeperCoachesTotalSeats());
        trainno2.setGeneralCoaches(railway.getGeneralCoaches());
        trainno2.setGeneralCoachesTotalSeats(railway.getGeneralCoachesTotalSeats());

        railwaysRepository.save(trainno2);
        return trainno2;
    }




    @Override
    public String deleteTrain(int trainNumber) throws TrainIsNotExistHereException {

        Optional<Optional> t = getTrainById(trainNumber);
        if (t!=null){
            railwaysRepository.deleteById(trainNumber);
        }

        return null;
    }



}

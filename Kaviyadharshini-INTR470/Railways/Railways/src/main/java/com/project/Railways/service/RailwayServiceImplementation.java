package com.project.Railways.service;

import com.project.Railways.exception.TrainNotFoundException;
import com.project.Railways.model.Railway;
import com.project.Railways.repository.RailwaysRepository;
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
    public Optional<Optional> getTrainById(int trainNumber) throws TrainNotFoundException  {
        Optional trainno1 = railwaysRepository.findById(trainNumber);
        if (trainno1.isEmpty()){
            throw new TrainNotFoundException ("Train is not exist ", "This Train Id is Invalid");
        }
        return Optional.of(trainno1);
    }




    @Override
    public Railway updateTrain(Railway railway) throws TrainNotFoundException {
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
    public String deleteTrain(int trainNumber) throws TrainNotFoundException {

        Optional<Optional> t = getTrainById(trainNumber);
        if (t!=null){
            railwaysRepository.deleteById(trainNumber);
        }

        return null;
    }



}

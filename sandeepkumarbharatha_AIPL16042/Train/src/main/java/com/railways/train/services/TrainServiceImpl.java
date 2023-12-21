package com.railways.train.services;

import com.railways.train.exceptions.TrainNumberNotFound;
import com.railways.train.model.Train;
import com.railways.train.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainServiceImpl implements TrainServices {
    @Autowired
    private TrainRepository trainRepository;

    public Train addTrainDetails(Train train) {

        return trainRepository.save(train);
    }

    @Override
    public Train getTrainByNumber(long trainNumToSearch) throws TrainNumberNotFound {
        Optional<Train> t = trainRepository.findById(trainNumToSearch);
        if (t.isEmpty()) {
            throw  new TrainNumberNotFound("Train  number not found sandeep try for other train number");
        }
        return t.get();
    }
    @Override
    public List<Train> getAllDetails() {
        return trainRepository.findAll();
    }

    @Override
    public Train updateTainDetails(Train train) throws TrainNumberNotFound {
        if (getTrainByNumber(train.getTrainNumber()) != null) {
            return trainRepository.save(train);
        }
        throw new TrainNumberNotFound("Train number not found sandeep try for other train number");
    }
    @Override
    public String deleteByTrainNumber(Long trainNumberToDelete) throws TrainNumberNotFound {
        if (getTrainByNumber(trainNumberToDelete) != null) {
            trainRepository.deleteById(trainNumberToDelete);
            return "details are deleted sucessfully of train number" + trainNumberToDelete;
        }
       throw new TrainNumberNotFound("Train number not found sandeep try for other train number");

    }

}

package trainmicroservice.trainmicroservice.service;

import trainmicroservice.trainmicroservice.model.Train;

import java.util.List;
import java.util.Optional;

public interface TrainService {
    Train addTrain(Train train);

    List<Train> getAllTrains();

    Optional<Train> getTrainByNo(int trainNo);

    Train updateTrain(Train train);

    String deleteTrainByNo(int trainNo);


}

package Service;

import Model.Train;
import Repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TrainService {

    private final TrainRepository trainRepository;

    @Autowired
    public TrainService(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    public Train createTrain(Train train) {
        return trainRepository.save(train);
    }

    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    public Optional<Train> getTrainById(Long id) {
        return trainRepository.findById(id);
    }

    public Train updateTrain(Long id, Train updatedTrain) {
        Optional<Train> existingTrainOptional = trainRepository.findById(id);
        if (existingTrainOptional.isPresent()) {
            updatedTrain.setId(id);
            return trainRepository.save(updatedTrain);
        }
        return null;
    }

    public boolean deleteTrainById(Long id) {
        Optional<Train> trainOptional = trainRepository.findById(id);
        if (trainOptional.isPresent()) {
            trainRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Train addTrain(Train train) {
        return train;
    }

    public boolean deleteTrain(Long id) {
        return false;
    }


    public Train getTrainDetailsById(long l) {
        return null;
    }
}

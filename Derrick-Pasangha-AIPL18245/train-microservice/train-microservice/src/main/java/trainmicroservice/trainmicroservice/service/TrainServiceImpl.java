package trainmicroservice.trainmicroservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainmicroservice.trainmicroservice.exception.TrainNoDoesNotExistException;
import trainmicroservice.trainmicroservice.model.Train;
import trainmicroservice.trainmicroservice.repository.Trainrepository;

import java.util.List;
import java.util.Optional;
@Service
public class TrainServiceImpl implements TrainService {
    private static final Logger logger = LoggerFactory.getLogger("logback");
    @Autowired
    private Trainrepository trainRepository;
    @Override
    public Train addTrain(Train train) {
        return trainRepository.save(train);
    }

    @Override
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    @Override
    public Optional<Train> getTrainByNo(int trainNo) {
        Optional<Train> tr = trainRepository.findById(trainNo);
            if (tr.isEmpty()) {
                logger.error("Train number is wrong");
                throw new TrainNoDoesNotExistException("Train does not exist");
            }
        return tr;
    }


    @Override
    public Train updateTrain(Train train) {
        Optional<Train> tr = getTrainByNo(train.getTrainNumber());
        if (tr == null) {
            logger.error("Train number is wrong");
            throw new TrainNoDoesNotExistException("Train does not exist");
        }
        return trainRepository.save(train);
    }

    @Override
    public String deleteTrainByNo(int trainNo) {
        String message;
        Optional<Train> tr=getTrainByNo(trainNo);
        if(tr==null)
        {
            logger.error("Train number is wrong");
            throw new TrainNoDoesNotExistException("Train does not exist");
        }
        trainRepository.deleteById(trainNo);
        message="Product deleted successfully";
        return message;
    }
}

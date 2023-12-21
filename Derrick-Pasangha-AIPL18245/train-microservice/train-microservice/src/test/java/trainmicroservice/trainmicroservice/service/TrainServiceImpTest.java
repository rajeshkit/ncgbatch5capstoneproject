package trainmicroservice.trainmicroservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import trainmicroservice.trainmicroservice.exception.TrainNoDoesNotExistException;
import trainmicroservice.trainmicroservice.model.Train;
import trainmicroservice.trainmicroservice.repository.Trainrepository;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class TrainServiceImplTest {

    @Mock
    private Trainrepository trainRepository;
    @InjectMocks
    private TrainServiceImpl trainServiceImp;
    @Test
    void addTrain() {
        Train p1 = Train.builder()
                .trainNumber(100).trainName("lalbagh").totalKms(300).acCoaches(2).generalCoaches(2).acCoachTotalSeats(100).generalCoachesTotalSeats(100).sleeperCoaches(2).sleeperCoachTotalSeats(100).build();
        Mockito.when(trainRepository.save(p1)).thenReturn(p1);
        assertEquals(p1,trainServiceImp.addTrain(p1));
    }

    @Test
    void getAllTrains() {
        Train p1 = Train.builder()
                .trainNumber(100).trainName("lalbagh").totalKms(300).acCoaches(2).generalCoaches(2).acCoachTotalSeats(100).generalCoachesTotalSeats(100).sleeperCoaches(2).sleeperCoachTotalSeats(100).build();
        Train p2 = Train.builder()
                .trainNumber(101).trainName("lalbagh").totalKms(300).acCoaches(2).generalCoaches(2).acCoachTotalSeats(100).generalCoachesTotalSeats(100).sleeperCoaches(2).sleeperCoachTotalSeats(100).build();
        Mockito.when(trainRepository.findAll()).thenReturn(Arrays.asList(p1,p2));
        assertEquals(2,trainServiceImp.getAllTrains().size());
    }

    @Test
    void getTrainById() throws TrainNoDoesNotExistException {
        Train p1 = Train.builder()
                .trainNumber(100).trainName("lalbagh").totalKms(300).acCoaches(2).generalCoaches(2).acCoachTotalSeats(100).generalCoachesTotalSeats(100).sleeperCoaches(2).sleeperCoachTotalSeats(100).build();
        Mockito.when(trainRepository.findById(100)).thenReturn(Optional.of(p1));
        assertEquals(100,trainServiceImp.getTrainByNo(100).get().getTrainNumber());
    }

    @Test
    void updateTrain() {
    }

    @Test
    void deleteTrainByNo() {
    }
}

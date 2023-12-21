package com.train.trainmicroservice.ServiceTest;

import com.train.trainmicroservice.entity.Train;
import com.train.trainmicroservice.exceptions.TrainNotFoundException;
import com.train.trainmicroservice.repository.TrainRepository;
import com.train.trainmicroservice.service.TrainService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TrainServiceTest {

    @Mock
    TrainRepository trainRepository;


    @InjectMocks
    TrainService trainService;



    @Test
    public void testAddTrain(){
        Train train = new Train();
        train.setTrainNumber(123);
        train.setTrainName("Express Train");
        train.setTotalKms(1500.5);
        train.setAcCoaches(2);
        train.setAcCoachTotalSeats(100);
        train.setSleeperCoaches(3);
        train.setSleeperCoachTotalSeats(150);
        train.setGeneralCoaches(5);
        train.setGeneralCoachTotalSeats(250);
        Mockito.when(trainRepository.save(train)).thenReturn(train);
     Train train1 =   trainService.addTrain(train);
        Assertions.assertEquals(123,train1.getTrainNumber());
    }

    @Test
    public void testFindTrain(){
        Train train = new Train();
        train.setTrainNumber(123);
        train.setTrainName("Express Train");
        train.setTotalKms(1500.5);
        train.setAcCoaches(2);
        train.setAcCoachTotalSeats(100);
        train.setSleeperCoaches(3);
        train.setSleeperCoachTotalSeats(150);
        train.setGeneralCoaches(5);
        train.setGeneralCoachTotalSeats(250);
        Mockito.when(trainRepository.findByTrainNumber(train.getTrainNumber())).thenReturn(train);
        Train train1 =   trainService.findTrain(train.getTrainNumber());
        Assertions.assertEquals(123,train1.getTrainNumber());
    }
    @Test
    public void testFindTrainWithException(){
        Train train = new Train();
        train.setTrainNumber(123);
        train.setTrainName("Express Train");
        train.setTotalKms(1500.5);
        train.setAcCoaches(2);
        train.setAcCoachTotalSeats(100);
        train.setSleeperCoaches(3);
        train.setSleeperCoachTotalSeats(150);
        train.setGeneralCoaches(5);
        train.setGeneralCoachTotalSeats(250);
        Mockito.when(trainRepository.findByTrainNumber(train.getTrainNumber())).thenReturn(null);
        assertThrows(TrainNotFoundException.class, () -> {
            trainService.findTrain(train.getTrainNumber());
        });
    }
}

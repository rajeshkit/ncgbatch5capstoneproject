package com.train.trainmicroservice.ControllerTest;

import com.train.trainmicroservice.controller.TrainController;
import com.train.trainmicroservice.entity.Train;
import com.train.trainmicroservice.service.TrainService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TrainControllerTest {


    @Mock
    TrainService trainService;

    @InjectMocks
    TrainController trainController;


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
        Mockito.when(trainService.addTrain(train)).thenReturn(train);
        Train trainObj=trainController.addTrain(train);
        Assertions.assertEquals(123,trainObj.getTrainNumber());
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
        Mockito.when(trainService.findTrain(123)).thenReturn(train);
        Train trainObj=trainController.findTrain(train.getTrainNumber());
        Assertions.assertEquals(123,trainObj.getTrainNumber());
    }
}

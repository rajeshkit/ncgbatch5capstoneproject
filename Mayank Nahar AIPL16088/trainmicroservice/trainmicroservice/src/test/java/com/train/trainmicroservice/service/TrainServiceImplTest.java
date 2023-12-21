package com.train.trainmicroservice.service;

import com.train.trainmicroservice.exception.TrainIdNotExistException;
import com.train.trainmicroservice.model.Train;
import com.train.trainmicroservice.repository.TrainRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TrainServiceImplTest {
    @Mock
    private TrainRepository trainRepository;
    @InjectMocks
    private TrainServiceImpl trainServiceImpl;


    @Test
    void addTrain() {
        Train train = Train.builder()
                .trainName("jp-udz")
                .trainNumber(123)
                .acCoaches(2).acCoachesTotalSeats(20)
                .generalCoaches(5).generalCoachTotalSeats(50)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50)
                .totalKms(200)
                .build();
        Mockito.when(trainRepository.save(train)).thenReturn(train);
        assertEquals(train, trainServiceImpl.addTrain(train));
    }

    @Test
    void getAllTrain() {
        Train train1 = Train.builder()
                .trainName("jp-udz")
                .trainNumber(123)
                .acCoaches(2).acCoachesTotalSeats(20)
                .generalCoaches(5).generalCoachTotalSeats(50)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50)
                .totalKms(200)
                .build();
        Train train2 = Train.builder()
                .trainName("jp-cor")
                .trainNumber(321)
                .acCoaches(3).acCoachesTotalSeats(20)
                .generalCoaches(5).generalCoachTotalSeats(50)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50)
                .totalKms(400)
                .build();
        Mockito.when(trainRepository.findAll()).thenReturn(Arrays.asList(train1, train2));
        assertEquals(2, trainServiceImpl.getAllTrain().size());

    }

    @Test
    void getTrainById() throws TrainIdNotExistException {
        Train train = Train.builder()
                .trainName("jp-udz")
                .trainNumber(12)
                .acCoaches(2).acCoachesTotalSeats(20)
                .generalCoaches(5).generalCoachTotalSeats(50)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50)
                .totalKms(200)
                .build();
        Mockito.when(trainRepository.findById(12)).thenReturn(Optional.of(train));
        assertEquals(12, trainServiceImpl.getTrainById(12).getTrainNumber());

    }

    @Test
    void getTrainByIdWithException() {
        Train train = Train.builder()
                .trainName("jp-udz")
                .trainNumber(12)
                .acCoaches(2).acCoachesTotalSeats(20)
                .generalCoaches(5).generalCoachTotalSeats(50)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50)
                .totalKms(200)
                .build();
        Mockito.when(trainRepository.findById(23)).thenReturn(Optional.empty());
        assertThrows(TrainIdNotExistException.class, () -> {
            trainServiceImpl.getTrainById(23);
        });
    }


    @Test
    void updateTrain() throws TrainIdNotExistException {
        Train train2 = Train.builder()
                .trainName("aj-udz")
                .trainNumber(123)
                .acCoaches(2).acCoachesTotalSeats(20)
                .generalCoaches(5).generalCoachTotalSeats(50)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50)
                .totalKms(200)
                .build();

        Mockito.when(trainRepository.save(train2)).thenReturn(train2);
        assertEquals(train2, trainServiceImpl.addTrain(train2));

    }


}
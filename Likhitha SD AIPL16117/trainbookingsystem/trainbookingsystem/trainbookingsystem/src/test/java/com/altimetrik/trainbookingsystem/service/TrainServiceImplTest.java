package com.altimetrik.trainbookingsystem.service;

import com.altimetrik.trainbookingsystem.exception.TrainNotExistsException;
import com.altimetrik.trainbookingsystem.model.Train;
import com.altimetrik.trainbookingsystem.respository.TrainRepository;
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

    TrainServiceImplTest() {
    }

    @Test
    void addTrain() {
        Train t1 = Train.builder()
                .trainNo(101).trainName("Mysore Express")
                .totalKms(1000).acCoachTotalSeats(10).sleeperCoachTotalSeats(20).generalCoachTotalSeats(30).build();
        Mockito.when(trainRepository.save(t1)).thenReturn(t1);
        assertEquals(t1,trainServiceImpl.addTrain(t1));
    }

    @Test
    void getAllTrain() {
        Train t1 = Train.builder()
                .trainNo(101).trainName("Mysore Express")
                .totalKms(1000).acCoachTotalSeats(10).sleeperCoachTotalSeats(20).generalCoachTotalSeats(30).build();
        Train t2 = Train.builder()
                .trainNo(201).trainName("Shatabdi")
                .totalKms(2000).acCoachTotalSeats(15).sleeperCoachTotalSeats(25).generalCoachTotalSeats(35).build();
        Mockito.when(trainRepository.findAll()).thenReturn(Arrays.asList(t1,t2));
        assertEquals(2,trainServiceImpl.getAlltrain().size());
    }

    @Test
    void getTrainByNo() throws TrainNotExistsException {
        Train t1 = Train.builder()
                .trainNo(101).trainName("Mysore Express")
                .totalKms(1000).acCoachTotalSeats(10).sleeperCoachTotalSeats(20).generalCoachTotalSeats(30).build();

        Mockito.when(trainRepository.findById(101)).thenReturn(Optional.of(t1));
        assertEquals(101,trainServiceImpl.getTrainByNo(101).getTrainNo());
    }
    @Test
    void getTrainByNoWithException()  {
        Train t1 = Train.builder()
                .trainNo(101).trainName("Mysore Express")
                .totalKms(1000).acCoachTotalSeats(10).sleeperCoachTotalSeats(20).generalCoachTotalSeats(30).build();
        Mockito.when(trainRepository.findById(400)).thenReturn(Optional.empty());
        assertThrows(TrainNotExistsException.class,
                ()->{trainServiceImpl.getTrainByNo(400);});
    }

    @Test
    void updateTrain() {
    }

    @Test
    void deleteTrainByNo() {
    }
}


package com.altimetrik.trainmicroservices.service;

import com.altimetrik.trainmicroservices.exception.TrainNumberAlreadyExistsException;
import com.altimetrik.trainmicroservices.exception.TrainNumberNotExistsException;
import com.altimetrik.trainmicroservices.model.Train;
import com.altimetrik.trainmicroservices.repository.TrainRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrainServiceImplTest {
    @Mock
    private TrainRepository trainRepository;
    @InjectMocks
    private TrainServiceImpl trainService;

    @Test
    void testAddTrain() throws TrainNumberAlreadyExistsException {
        Train train = Train.builder().trainNumber(1).trainName("Express").totalKms(500.0).acCoaches(2).acCoachTotalSeats(50).sleeperCoaches(5).sleeperCoachTotalSeats(200).generalCoaches(10).generalCoachTotalSeats(500).build();
        when(trainRepository.save(Mockito.any(Train.class))).thenReturn(train);
        Train addedTrain = trainService.addTrain(train);
        assertNotNull(addedTrain);
        assertEquals(train.getTrainNumber(), addedTrain.getTrainNumber());
    }

    @Test
    void testGetAllTrains() {
        List<Train> trains = Arrays.asList(new Train(), new Train(), new Train());
        when(trainRepository.findAll()).thenReturn(trains);
        List<Train> resultTrains = trainService.getAllTrains();
        assertNotNull(resultTrains);
        assertEquals(trains.size(), resultTrains.size());
    }

    @Test
    void testGetTrainByTrainNumber() throws TrainNumberNotExistsException {
        Train train = Train.builder().trainNumber(1).trainName("Express").totalKms(500.0).acCoaches(2).acCoachTotalSeats(50).sleeperCoaches(5).sleeperCoachTotalSeats(200).generalCoaches(10).generalCoachTotalSeats(500).build();
        when(trainRepository.findById(1L)).thenReturn(Optional.of(train));
        Train resultTrain = trainService.getTrainByTrainNumber(1);
        assertNotNull(resultTrain);
        assertEquals(train.getTrainNumber(), resultTrain.getTrainNumber());
    }

    @Test
    void testUpdateTrain() throws TrainNumberNotExistsException {
        Train train = Train.builder().trainNumber(1).trainName("Express").build();
        when(trainRepository.save(any(Train.class))).thenReturn(train);
        when(trainRepository.findById(1L)).thenReturn(Optional.of(train));
        Train result = trainService.updateTrain(train);
        assertNotNull(result);
        assertEquals(1, result.getTrainNumber());
        assertEquals("Express", result.getTrainName());
    }

    @Test
    void testDeleteTrainByTrainNumber() {
        long trainNumberToDelete = 123;
        when(trainRepository.findById(trainNumberToDelete)).thenThrow(new RuntimeException("Database error"));
        assertThrows(RuntimeException.class, () -> trainService.deleteTrainByTrainNumber(trainNumberToDelete));
        verify(trainRepository, never()).deleteById(anyLong());
    }
}
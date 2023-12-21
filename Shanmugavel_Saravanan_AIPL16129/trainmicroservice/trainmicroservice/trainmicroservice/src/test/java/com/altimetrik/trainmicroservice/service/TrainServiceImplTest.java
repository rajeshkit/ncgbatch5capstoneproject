package com.altimetrik.trainmicroservice.service;

import com.altimetrik.trainmicroservice.exception.TrainNumberAlreadyExists;
import com.altimetrik.trainmicroservice.exception.TrainNumberNotFoundException;
import com.altimetrik.trainmicroservice.model.Train;
import com.altimetrik.trainmicroservice.repository.TrainRepository;
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
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class TrainServiceImplTest {

    @Mock
    private TrainRepository trainRepository;

    @InjectMocks
    private TrainServiceImpl trainService;

    @Test
    void addTrain() throws TrainNumberAlreadyExists {
        Train train = new Train();
        train.setTrainNumber(1);
        train.setTrainName("Express");
        train.setTotalKms(500.0);
        train.setAcCoaches(2);
        train.setAcCoachTotalSeats(50);
        train.setSleeperCoaches(5);
        train.setSleeperCoachTotalSeats(200);
        train.setGeneralCoaches(10);
        train.setGeneralCoachTotalSeats(500);

        Mockito.when(trainRepository.save(any(Train.class))).
                thenReturn(train);

        assertEquals(train, trainService.addTrain(train));
    }

    @Test
    void getAllTrain() {
        Train train1 = new Train();
        train1.setTrainNumber(1);
        train1.setTrainName("Express");
        train1.setTotalKms(500.0);
        train1.setAcCoaches(2);
        train1.setAcCoachTotalSeats(50);
        train1.setSleeperCoaches(5);
        train1.setSleeperCoachTotalSeats(200);
        train1.setGeneralCoaches(10);
        train1.setGeneralCoachTotalSeats(500);

        Train train2 = new Train();
        train2.setTrainNumber(2);
        train2.setTrainName("Local");
        train2.setTotalKms(100.0);
        train2.setAcCoaches(1);
        train2.setAcCoachTotalSeats(20);
        train2.setSleeperCoaches(3);
        train2.setSleeperCoachTotalSeats(100);
        train2.setGeneralCoaches(5);
        train2.setGeneralCoachTotalSeats(250);

        Mockito.when(trainRepository.findAll()).thenReturn(Arrays.asList(train1, train2));

        List<Train> trains = trainService.getAllTrain();
        assertEquals(2, trains.size());
    }

    @Test
    void getTrainByNumber() throws TrainNumberNotFoundException {
        Train train = new Train();
        train.setTrainNumber(1);
        train.setTrainName("Express");
        train.setTotalKms(500.0);
        train.setAcCoaches(2);
        train.setAcCoachTotalSeats(50);
        train.setSleeperCoaches(5);
        train.setSleeperCoachTotalSeats(200);
        train.setGeneralCoaches(10);
        train.setGeneralCoachTotalSeats(500);

        Mockito.when(trainRepository.findById(1)).thenReturn(Optional.of(train));

        assertEquals(train, trainService.getTrainByNumber(1));
    }

    @Test
    void getTrainByNumberWithException() {
        Mockito.when(trainRepository.findById(100)).thenReturn(Optional.empty());

        assertThrows(TrainNumberNotFoundException.class,
                () -> trainService.getTrainByNumber(100));
    }

    @Test
    void updateTrain() throws TrainNumberNotFoundException {
        Train existingTrain = new Train();
        existingTrain.setTrainNumber(1);
        existingTrain.setTrainName("Express");
        existingTrain.setTotalKms(500.0);
        existingTrain.setAcCoaches(2);
        existingTrain.setAcCoachTotalSeats(50);
        existingTrain.setSleeperCoaches(5);
        existingTrain.setSleeperCoachTotalSeats(200);
        existingTrain.setGeneralCoaches(10);
        existingTrain.setGeneralCoachTotalSeats(500);

        Mockito.when(trainRepository.findById(1)).thenReturn(Optional.of(existingTrain));
        Mockito.when(trainRepository.save(any(Train.class))).thenReturn(existingTrain);

        Train updatedTrain = trainService.updateTrain(existingTrain);

        assertNotNull(updatedTrain);
        assertEquals("Express", updatedTrain.getTrainName());
    }

    @Test
    void deleteTrainByNumber() throws TrainNumberNotFoundException {
        Train train = new Train();
        train.setTrainNumber(1);
        train.setTrainName("Express");
        train.setTotalKms(500.0);
        train.setAcCoaches(2);
        train.setAcCoachTotalSeats(50);
        train.setSleeperCoaches(5);
        train.setSleeperCoachTotalSeats(200);
        train.setGeneralCoaches(10);
        train.setGeneralCoachTotalSeats(500);

        Mockito.when(trainRepository.findById(1)).thenReturn(Optional.of(train));

        String result = trainService.deleteTrainByNumber(1);
        assertEquals("Train deleted successfully", result);
    }

    @Test
    void deleteTrainByNumberWithException() {
        Mockito.when(trainRepository.findById(100)).thenReturn(Optional.empty());

        assertThrows(TrainNumberNotFoundException.class,
                () -> trainService.deleteTrainByNumber(100));
    }
}


package com.altimetrik.trainbooking.service;

import com.altimetrik.trainbooking.exception.TrainNumberNotExistsException;
import com.altimetrik.trainbooking.model.Train;
import com.altimetrik.trainbooking.repository.TrainRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
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
        Train t1 = Train.builder()
                .trainNumber(100).trainName("Vande Bharat")
                .totalKilometers(7546).totalAcCoachSeats(15).totalSleeperCoachSeats(18).totalGeneralCoachSeats(25).build();
        Mockito.when(trainRepository.save(t1)).thenReturn(t1);
        assertEquals(t1,trainServiceImpl.addTrain(t1));
    }

    @Test
    void getAllTrain() {
        Train t1 = Train.builder()
                .trainNumber(100).trainName("Vande Bharat")
                .totalKilometers(7546).totalAcCoachSeats(15).totalSleeperCoachSeats(18).totalSleeperCoachSeats(25).build();
        Train t2 = Train.builder()
                .trainNumber(101).trainName("Chennai Express")
                .totalKilometers(10000).totalAcCoachSeats(20).totalSleeperCoachSeats(20).totalGeneralCoachSeats(20).build();
        Mockito.when(trainRepository.findAll()).thenReturn(Arrays.asList(t1,t2));
        assertEquals(2,trainServiceImpl.getAlltrain().size());
    }

    @Test
    void getTrainByNumber() throws TrainNumberNotExistsException {
        Train t1 = Train.builder()
                .trainNumber(100).trainName("Vandhe Bharat")
                .totalKilometers(7546).totalAcCoachSeats(18).totalSleeperCoachSeats(15).totalGeneralCoachSeats(25).build();

        Mockito.when(trainRepository.findById(100)).thenReturn(Optional.of(t1));
        assertEquals(100,trainServiceImpl.getTrainByNumber(100).getTrainNumber());
    }
    @Test
    void getTrainByNumberWithException()  {
        Train t1 = Train.builder()
                .trainNumber(100).trainName("Vandhe Bharat")
                .totalKilometers(7546).totalAcCoachSeats(18).totalSleeperCoachSeats(15).totalGeneralCoachSeats(25).build();
        Mockito.when(trainRepository.findById(400)).thenReturn(Optional.empty());
        assertThrows(TrainNumberNotExistsException.class,
                ()->{trainServiceImpl.getTrainByNumber(400);});
    }

    @Test
    void updateTrain() {
    }

    @Test
    void deleteTrainByNumber() {
    }
}
package com.altimetrik.train.service;

import com.altimetrik.train.exception.TrainNumberNotFoundException;
import com.altimetrik.train.model.Train;
import com.altimetrik.train.repository.TrainRepository;
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
    private TrainServiceImpl trainService;

    Train t1=Train.builder()
            .trainNo(10001).trainName("Charminar Express")
            .totalKms(700).acCoaches(10).acTotalSeats(720)
            .sleeperCoaches(10).sleeperTotalSeats(720)
            .generalCoaches(2).generalTotalSeats(144).build();
    Train t2=Train.builder()
            .trainNo(10002).trainName("Golconda Express")
            .totalKms(800).acCoaches(10).acTotalSeats(720)
            .sleeperCoaches(10).sleeperTotalSeats(720)
            .generalCoaches(2).generalTotalSeats(144).build();

    @Test
    void addTrain() {
        Mockito.when(trainRepository.save(t1)).thenReturn(t1);
        assertEquals(t1,trainService.addTrain(t1));
    }

    @Test
    void viewAllTrains() {
        Mockito.when(trainRepository.findAll()).thenReturn(Arrays.asList(t1,t2));
        assertEquals(2,trainService.viewAllTrains().size());
    }

    @Test
    void getTrainById() throws TrainNumberNotFoundException {
        Mockito.when(trainRepository.findById(10002)).thenReturn(Optional.of(t2));
        assertEquals(10002,trainService.getTrainById(10002).getTrainNo());
    }
    @Test
    void getTrainByIdWithException() {
        Mockito.when(trainRepository.findById(500)).thenReturn(Optional.empty());
        assertThrows(TrainNumberNotFoundException.class,
                ()->trainService.getTrainById(500));
    }

    @Test
    void updateTrain() throws TrainNumberNotFoundException {
        Mockito.when(trainRepository.findById(10001)).thenReturn(Optional.of(t1));
        Mockito.when(trainRepository.save(t1)).thenReturn(t1);
        assertEquals(10001,trainService.updateTrain(t1).getTrainNo());
    }

    @Test
    void updateTrainWithException() {
        Mockito.when(trainRepository.findById(10001)).thenReturn(Optional.empty());
        assertThrows(TrainNumberNotFoundException.class,()->trainService.updateTrain(t1));
    }

    @Test
    void deleteTrainById() throws TrainNumberNotFoundException{
        Mockito.when(trainRepository.findById(10001)).thenReturn(Optional.of(t1));
        assertEquals("Train details Deleted Successfully for Train: " +t1.getTrainNo(),trainService.deleteTrainById(10001));
    }

    @Test
    void deleteTrainByIdWithException(){
        Mockito.when(trainRepository.findById(10008)).thenReturn(Optional.empty());
        assertThrows(TrainNumberNotFoundException.class,
                ()->trainService.deleteTrainById(10008));
    }
}
package com.railways.train.service;

import com.railways.train.exception.TrainWithThatNumberExists;
import com.railways.train.exception.TrainNumberNotFoundException;
import com.railways.train.model.Train;
import com.railways.train.repository.TrainRepository;
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
    void addTrain() throws TrainWithThatNumberExists {
       Train t= Train.builder().trainNumber(123).trainName("ABC").totalKms(100).acCoaches(10).acCoachTotalSeats(100).sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(8).generalCoachTotalSeats(80).build();
       Mockito.when(trainRepository.findById(123L)).thenReturn(Optional.empty());
       Mockito.when(trainRepository.save(t)).thenReturn(t);
       assertEquals(t,trainServiceImpl.addTrain(t));
    }

    @Test
    void addTrainWithException() throws TrainWithThatNumberExists {
        Train t= Train.builder().trainNumber(123).trainName("ABC").totalKms(100).acCoaches(10).acCoachTotalSeats(100).sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(8).generalCoachTotalSeats(80).build();
        Mockito.when(trainRepository.findById(123L)).thenReturn(Optional.of(t));
        assertThrows(TrainWithThatNumberExists.class,()->{trainServiceImpl.addTrain(t);});
    }

    @Test
    void getAllTrains() {
        Train t1= Train.builder().trainNumber(456).trainName("DEF").totalKms(200).acCoaches(9).acCoachTotalSeats(100).sleeperCoaches(4).sleeperCoachTotalSeats(50).generalCoaches(6).generalCoachTotalSeats(80).build();
        Train t2= Train.builder().trainNumber(123).trainName("ABC").totalKms(100).acCoaches(10).acCoachTotalSeats(100).sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(8).generalCoachTotalSeats(80).build();
        Mockito.when(trainRepository.findAll()).thenReturn(Arrays.asList(t1,t2));
        assertEquals(2,trainServiceImpl.getAllTrains().size());
    }

    @Test
    void getTrainById() throws TrainNumberNotFoundException {
        Train t= Train.builder().trainNumber(123).trainName("ABC").totalKms(100).acCoaches(10).acCoachTotalSeats(100).sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(8).generalCoachTotalSeats(80).build();
        Mockito.when(trainRepository.findById(123L)).thenReturn(Optional.of(t));
        assertEquals(t,trainServiceImpl.getTrainById(123));
    }
    @Test
    void getTrainByIdWithException() throws TrainNumberNotFoundException {
        Train t= Train.builder().trainNumber(123).trainName("ABC").totalKms(100).acCoaches(10).acCoachTotalSeats(100).sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(8).generalCoachTotalSeats(80).build();
        Mockito.when(trainRepository.findById(500L)).thenReturn(Optional.empty());
        assertThrows(TrainNumberNotFoundException.class,()->{trainServiceImpl.getTrainById(500);});
    }

    @Test
    void updateTrain() throws TrainNumberNotFoundException {
        Train t= Train.builder().trainNumber(123).trainName("ABC").totalKms(100).acCoaches(10).acCoachTotalSeats(100).sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(8).generalCoachTotalSeats(80).build();
        Mockito.when(trainRepository.findById(123L)).thenReturn(Optional.of(t));
        Mockito.when(trainRepository.save(t)).thenReturn(t);
        assertEquals(t,trainServiceImpl.updateTrain(t));
    }
    @Test
    void updateTrainWithException() throws TrainNumberNotFoundException {
        Train t= Train.builder().trainNumber(123).trainName("ABC").totalKms(100).acCoaches(10).acCoachTotalSeats(100).sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(8).generalCoachTotalSeats(80).build();
        Mockito.when(trainRepository.findById(123L)).thenReturn(Optional.empty());
        assertThrows(TrainNumberNotFoundException.class,()->{trainServiceImpl.updateTrain(t);});
    }

    @Test
    void deleteTrainById() throws TrainNumberNotFoundException {
        Train t= Train.builder().trainNumber(123).trainName("ABC").totalKms(100).acCoaches(10).acCoachTotalSeats(100).sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(8).generalCoachTotalSeats(80).build();
        Mockito.when(trainRepository.findById(123L)).thenReturn(Optional.of(t));
        assertEquals("Train Deleted Successfully",trainServiceImpl.deleteTrainById(123L));
    }
    @Test
    void deleteTrainByIdWithException() throws TrainNumberNotFoundException {
        Train t= Train.builder().trainNumber(123).trainName("ABC").totalKms(100).acCoaches(10).acCoachTotalSeats(100).sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(8).generalCoachTotalSeats(80).build();
        Mockito.when(trainRepository.findById(500L)).thenReturn(Optional.empty());
        assertThrows(TrainNumberNotFoundException.class,()->{trainServiceImpl.deleteTrainById(500L);});
    }
}
package com.train.trainmicroservice.service;

import com.train.trainmicroservice.exception.TrainNumberNotExistsException;
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
       Train train= Train.builder().trainNumber(1005).trainName("Kachiguda Express")
               .acCoaches(10).acCoachTotalSeats(300)
               .sleeperCoaches(20).sleeperCoachTotalSeats(500)
               .generalCoaches(40).generalCoachesTotalSeats(800).totalKms(1200)
               .build();
        Mockito.when(trainRepository.save(train)).thenReturn(train);
        assertEquals(train,trainServiceImpl.addTrain(train));

    }

    @Test
    void getAllTrains() {
        Train train= Train.builder().trainNumber(1005).trainName("Kachiguda Express")
                .acCoaches(10).acCoachTotalSeats(300)
                .sleeperCoaches(20).sleeperCoachTotalSeats(500)
                .generalCoaches(40).generalCoachesTotalSeats(800).totalKms(1200)
                .build();
        Train train1= Train.builder().trainNumber(1008).trainName("Mumbai Express")
                .acCoaches(8).acCoachTotalSeats(280)
                .sleeperCoaches(15).sleeperCoachTotalSeats(300)
                .generalCoaches(40).generalCoachesTotalSeats(800).totalKms(1500)
                .build();
       Mockito.when(trainRepository.findAll()).thenReturn(Arrays.asList(train,train1));
       assertEquals(2,trainServiceImpl.getAllTrains().size());

    }

    @Test
    void getTrainByTrainNo() throws TrainNumberNotExistsException{
        Train train= Train.builder().trainNumber(1008).trainName("Mumbai Express")
                .acCoaches(8).acCoachTotalSeats(280)
                .sleeperCoaches(15).sleeperCoachTotalSeats(300)
                .generalCoaches(40).generalCoachesTotalSeats(800).totalKms(1500)
                .build();
        Mockito.when(trainRepository.findById(1008)).thenReturn(Optional.of(train));
        assertEquals(1008,trainServiceImpl.getTrainByTrainNo(1008).getTrainNumber());
    }

    @Test
    void getTrainByTrainNoWithException() {
        Train train= Train.builder().trainNumber(1008).trainName("Mumbai Express")
                .acCoaches(8).acCoachTotalSeats(280)
                .sleeperCoaches(15).sleeperCoachTotalSeats(300)
                .generalCoaches(40).generalCoachesTotalSeats(800).totalKms(1500)
                .build();
        Mockito.when(trainRepository.findById(500)).thenReturn(Optional.empty());
        assertThrows(TrainNumberNotExistsException.class,
                ()->trainServiceImpl.getTrainByTrainNo(500));
    }


    @Test
    void updateTrain() throws TrainNumberNotExistsException {
        Train train= Train.builder().trainNumber(1008).trainName("Mumbai Express")
                .acCoaches(8).acCoachTotalSeats(280)
                .sleeperCoaches(15).sleeperCoachTotalSeats(300)
                .generalCoaches(40).generalCoachesTotalSeats(800).totalKms(1500)
                .build();
        Mockito.when(trainRepository.findById(1008)).thenReturn(Optional.of(train));
        Mockito.when(trainRepository.save(train)).thenReturn(train);
        assertEquals(1008,trainServiceImpl.updateTrain(train).getTrainNumber());

    }


    @Test
    void updateTrainWithException() {
        Train train= Train.builder().trainNumber(1008).trainName("Mumbai Express")
                .acCoaches(8).acCoachTotalSeats(280)
                .sleeperCoaches(15).sleeperCoachTotalSeats(300)
                .generalCoaches(40).generalCoachesTotalSeats(800).totalKms(1500)
                .build();
        Mockito.when(trainRepository.findById(1008)).thenReturn(Optional.empty());
        assertThrows(TrainNumberNotExistsException.class,
                ()->trainServiceImpl.updateTrain(train));

    }


    @Test
    void deleteTrainByTrainNo() throws TrainNumberNotExistsException{
        Train train= Train.builder().trainNumber(1008).trainName("Mumbai Express")
                .acCoaches(8).acCoachTotalSeats(280)
                .sleeperCoaches(15).sleeperCoachTotalSeats(300)
                .generalCoaches(40).generalCoachesTotalSeats(800).totalKms(1500)
                .build();
        Mockito.when(trainRepository.findById(1008)).thenReturn(Optional.of(train));

        assertEquals("Train Deleted Successfully",trainServiceImpl.deleteTrainByTrainNo(1008));


    }

    @Test
    void deleteTrainByTrainNoWithException() {
        Train train= Train.builder().trainNumber(1008).trainName("Mumbai Express")
                .acCoaches(8).acCoachTotalSeats(280)
                .sleeperCoaches(15).sleeperCoachTotalSeats(300)
                .generalCoaches(40).generalCoachesTotalSeats(800).totalKms(1500)
                .build();
        Mockito.when(trainRepository.findById(1009)).thenReturn(Optional.empty());
        assertThrows(TrainNumberNotExistsException.class,
                ()->trainServiceImpl.deleteTrainByTrainNo(1009));

    }
}
package com.altimetrik.trainmicroservice.service;

import com.altimetrik.trainmicroservice.exception.TrainNumberAlreadyExistsException;
import com.altimetrik.trainmicroservice.exception.TrainNumberNotExistsException;
import com.altimetrik.trainmicroservice.model.Train;
import com.altimetrik.trainmicroservice.repository.TrainRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrainServiceImplTest {
    @Mock
    private TrainRepository trainRepository;
    @InjectMocks
    private TrainServiceImpl trainServiceImpl;

    @Test
    void addTrain() throws TrainNumberAlreadyExistsException {

        Train t = Train.builder()
                .trainNumber(20)
                .trainName("Super Express")
                .totalKms(100)
                .acCoaches(15)
                .acCoachTotalSeats(20)
                .sleeperCoaches(8)
                .sleeperCoachTotalSeats(12)
                .generalCoaches(20)
                .generalCoachTotalSeats(28).build();

        when(trainRepository.save(t)).thenReturn(t);
        assertEquals(t, trainServiceImpl.addTrain(t));
    }

   @Test
   void addTrainWithException(){

       Train t = Train.builder()
               .trainNumber(20)
               .trainName("Super Express")
               .totalKms(100)
               .acCoaches(15)
               .acCoachTotalSeats(20)
               .sleeperCoaches(8)
               .sleeperCoachTotalSeats(12)
               .generalCoaches(20)
               .generalCoachTotalSeats(28).build();

        when(trainRepository.findById(20)).thenReturn(Optional.empty());
        assertThrows(TrainNumberNotExistsException.class,
                () -> trainServiceImpl.getTrainByNumber(20));
   }

    @Test
    void getAllTrains() {
        Train t1 = Train.builder()
                .trainNumber(20)
                .trainName("Super Express")
                .totalKms(100)
                .acCoaches(15)
                .acCoachTotalSeats(20)
                .sleeperCoaches(8)
                .sleeperCoachTotalSeats(12)
                .generalCoaches(20)
                .generalCoachTotalSeats(28).build();

        Train t2 = Train.builder()
                .trainNumber(21)
                .trainName("Express123")
                .totalKms(200)
                .acCoaches(15)
                .acCoachTotalSeats(20)
                .sleeperCoaches(8)
                .sleeperCoachTotalSeats(10)
                .generalCoaches(18)
                .generalCoachTotalSeats(26).build();

        when(trainRepository.findAll()).thenReturn(Arrays.asList(t1, t2));
        assertEquals(2, trainServiceImpl.getAllTrains().size());
    }

    @Test
    void getTrainByNumber() throws TrainNumberNotExistsException {

        Train t = Train.builder()
                .trainNumber(20)
                .trainName("Super Express")
                .acCoaches(15)
                .acCoachTotalSeats(20)
                .sleeperCoaches(8)
                .sleeperCoachTotalSeats(12)
                .generalCoaches(20)
                .generalCoachTotalSeats(28).build();

        when(trainRepository.findById(20)).thenReturn(Optional.of(t));
        assertEquals(20, trainServiceImpl
                .getTrainByNumber(20)
                .getTrainNumber());
    }

    @Test
    void getTrainByNumberWithException() {

        Train t = Train.builder()
                .trainNumber(20)
                .trainName("Super Express")
                .totalKms(100)
                .acCoaches(15)
                .acCoachTotalSeats(20)
                .sleeperCoaches(8)
                .sleeperCoachTotalSeats(12)
                .generalCoaches(20)
                .generalCoachTotalSeats(28).build();

        when(trainRepository.findById(40)).thenReturn(Optional.empty());
        assertThrows(TrainNumberNotExistsException.class,
                () -> trainServiceImpl.getTrainByNumber(40));
    }

    @Test
    void updateTrain() throws TrainNumberNotExistsException {

        Train train = Train.builder()
                .trainNumber(20)
                .trainName("Super Express")
                .totalKms(100)
                .acCoaches(15)
                .acCoachTotalSeats(20)
                .sleeperCoaches(8)
                .sleeperCoachTotalSeats(12)
                .generalCoaches(20)
                .generalCoachTotalSeats(28).build();


        Train updatedTrain = Train.builder()
                .trainNumber(20)
                .trainName("Super Express 123")
                .acCoaches(16)
                .acCoachTotalSeats(21)
                .sleeperCoaches(8)
                .sleeperCoachTotalSeats(13)
                .generalCoaches(20)
                .generalCoachTotalSeats(25).build();

        when(trainRepository.findById(20)).thenReturn(Optional.of(train));
        when(trainRepository.save(updatedTrain)).thenReturn(updatedTrain);
        assertEquals(updatedTrain, trainServiceImpl.updateTrain(updatedTrain));

    }

    @Test
    void deleteTrainByNumber() throws TrainNumberNotExistsException {

        Train t = Train.builder()
                .trainNumber(20)
                .trainName("Super Express")
                .totalKms(100)
                .acCoaches(15)
                .acCoachTotalSeats(20)
                .sleeperCoaches(8)
                .sleeperCoachTotalSeats(12)
                .generalCoaches(20)
                .generalCoachTotalSeats(28).build();

        when(trainRepository.findById(20)).thenReturn(Optional.of(t));
        assertEquals("Train deleted successfully", trainServiceImpl.deleteTrainByNumber(20));
    }
}
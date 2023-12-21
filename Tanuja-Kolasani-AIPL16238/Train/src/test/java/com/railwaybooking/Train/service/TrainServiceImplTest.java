package com.railwaybooking.Train.service;

import com.railwaybooking.Train.exception.TrainNumberNotExistException;
import com.railwaybooking.Train.model.TrainInfo;
import com.railwaybooking.Train.repository.TrainRepository;
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

@ExtendWith(MockitoExtension.class)
 class TrainServiceImplTest {
    @Mock
    private TrainRepository trainRepository;
    @InjectMocks
    private TrainServiceImpl trainServiceImpl;

    void addTrain(){
        TrainInfo t=TrainInfo.builder().trainNumber(76542).trainName("Sarkar").totalKms(200).acCoaches(8).acCoachTotalSeats(80).sleeperCoaches(6).sleeperCoachTotalSeats(50).generalCoaches(4).generalCoachTotalSeats(80).build();
        Mockito.when(trainRepository.save(t)).thenReturn(t);
        assertEquals(t,trainServiceImpl.addTrain(t));
    }

    @Test
    void getAllTrains(){
        TrainInfo t1=TrainInfo.builder().trainNumber(456).trainName("DEF").totalKms(200).acCoaches(9).acCoachTotalSeats(100).sleeperCoaches(4).sleeperCoachTotalSeats(50).generalCoaches(6).generalCoachTotalSeats(80).build();
        TrainInfo t2=TrainInfo.builder().trainNumber(76542).trainName("Sarkar").totalKms(200).acCoaches(8).acCoachTotalSeats(80).sleeperCoaches(6).sleeperCoachTotalSeats(50).generalCoaches(4).generalCoachTotalSeats(80).build();
        Mockito.when(trainRepository.findAll()).thenReturn(Arrays.asList(t1,t2));
        assertEquals(2,trainServiceImpl.getAllTrains().size());
    }

    @Test
    void getTrainByNumber() throws TrainNumberNotExistException{
        TrainInfo t=TrainInfo.builder().trainNumber(76542).trainName("Sarkar").totalKms(200).acCoaches(8).acCoachTotalSeats(80).sleeperCoaches(6).sleeperCoachTotalSeats(50).generalCoaches(4).generalCoachTotalSeats(80).build();
        Mockito.when(trainRepository.findById(76542L)).thenReturn(Optional.of(t));
        assertEquals(t,trainServiceImpl.getTrainByNumber(76542));
    }

    @Test
    void getTrainByNumberWithException() throws TrainNumberNotExistException{
        TrainInfo t=TrainInfo.builder().trainNumber(76542).trainName("Sarkar").totalKms(200).acCoaches(8).acCoachTotalSeats(80).sleeperCoaches(6).sleeperCoachTotalSeats(50).generalCoaches(4).generalCoachTotalSeats(80).build();
        Mockito.when(trainRepository.findById(500L)).thenReturn(Optional.empty());
        assertThrows(TrainNumberNotExistException.class,()->{trainServiceImpl.getTrainByNumber(500);});
    }
    @Test
    void updateTrainInfo() throws TrainNumberNotExistException{
        TrainInfo t=TrainInfo.builder().trainNumber(76542).trainName("Sarkar").totalKms(200).acCoaches(8).acCoachTotalSeats(80).sleeperCoaches(6).sleeperCoachTotalSeats(50).generalCoaches(4).generalCoachTotalSeats(80).build();
        Mockito.when(trainRepository.findById(76542L)).thenReturn(Optional.of(t));
        Mockito.when(trainRepository.save(t)).thenReturn(t);
        assertEquals(t,trainServiceImpl.updateTrainInfo(t));
    }

    @Test
    void updateTrainInfoWithException() throws TrainNumberNotExistException{
        TrainInfo t=TrainInfo.builder().trainNumber(76542).trainName("Sarkar").totalKms(200).acCoaches(8).acCoachTotalSeats(80).sleeperCoaches(6).sleeperCoachTotalSeats(50).generalCoaches(4).generalCoachTotalSeats(80).build();
        Mockito.when(trainRepository.findById(76542L)).thenReturn(Optional.empty());
        assertThrows(TrainNumberNotExistException.class,()->{trainServiceImpl.updateTrainInfo(t);});
    }

    @Test
    void deleteTrainByNumber() throws TrainNumberNotExistException{
        TrainInfo t=TrainInfo.builder().trainNumber(76542).trainName("Sarkar").totalKms(200).acCoaches(8).acCoachTotalSeats(80).sleeperCoaches(6).sleeperCoachTotalSeats(50).generalCoaches(4).generalCoachTotalSeats(80).build();
        Mockito.when(trainRepository.findById(76542L)).thenReturn(Optional.of(t));
        assertEquals("train deleted successfully",trainServiceImpl.deleteTrainByNumber(76542L));
    }

    @Test
    void deleteTrainByNumberWithException() throws TrainNumberNotExistException{
        TrainInfo t=TrainInfo.builder().trainNumber(76542).trainName("Sarkar").totalKms(200).acCoaches(8).acCoachTotalSeats(80).sleeperCoaches(6).sleeperCoachTotalSeats(50).generalCoaches(4).generalCoachTotalSeats(80).build();
        Mockito.when(trainRepository.findById(500L)).thenReturn(Optional.empty());
        assertThrows(TrainNumberNotExistException.class,()->{trainServiceImpl.deleteTrainByNumber(500L);});
    }
}

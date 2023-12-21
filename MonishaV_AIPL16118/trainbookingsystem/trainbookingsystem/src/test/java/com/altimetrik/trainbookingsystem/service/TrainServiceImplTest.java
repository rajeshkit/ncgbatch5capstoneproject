package com.altimetrik.trainbookingsystem.service;

import com.altimetrik.trainbookingsystem.exception.TrainNoNotExistsException;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TrainServiceImplTest {
    @Mock
    private TrainRepository trainRepository;
    @InjectMocks
    private TrainServiceImpl trainServiceImpl;
    @Test
    void addTrain() {
        Train t1 = Train.builder()
                .trainNo("1").trainName("Vande Bharath").totalKms(18).
                acCoaches(5).acCoachTotalSeats(17).sleeperCoaches(5).
                sleeperCoachTotalSeats(6).generalCoaches(25).generalCoacheTotalSeats(30).build();
        Mockito.when(trainRepository.save(t1)).thenReturn(t1);
        assertEquals(t1,trainServiceImpl.addTrain(t1));
    }

    @Test
    void getAllTrain() {
        Train t1 = Train.builder()
                .trainNo("1").trainName("Vande Bharath").totalKms(18).
                acCoaches(5).acCoachTotalSeats(17).sleeperCoaches(5).
                sleeperCoachTotalSeats(6).generalCoaches(25).generalCoacheTotalSeats(30).build();
        Train t2 = Train.builder()
                .trainNo("2").trainName("Shatabdi Express").totalKms(23).
                acCoaches(67).acCoachTotalSeats(80).sleeperCoaches(42).
                sleeperCoachTotalSeats(56).generalCoaches(67).generalCoacheTotalSeats(87).build();
        Mockito.when(trainRepository.findAll()).thenReturn(Arrays.asList(t1,t2));
        assertEquals(2,trainServiceImpl.getAllTrain().size());
    }

    @Test
    void getTrainByNo() throws TrainNoNotExistsException {
        Train t1 = Train.builder()
                .trainNo("1").trainName("Vande Bharath").totalKms(18).
                acCoaches(5).acCoachTotalSeats(17).sleeperCoaches(5).
                sleeperCoachTotalSeats(6).generalCoaches(25).generalCoacheTotalSeats(30).build();

        Mockito.when(trainRepository.findById("1")).thenReturn(Optional.of(t1));
        assertEquals("1",trainServiceImpl.getTrainByNo("1").getTrainNo());
    }

    @Test
    void getTrainByNoWithException() {
        Train t1 = Train.builder()
                .trainNo("1").trainName("Vande Bharath").totalKms(18).
                acCoaches(5).acCoachTotalSeats(17).sleeperCoaches(5).
                sleeperCoachTotalSeats(6).generalCoaches(25).generalCoacheTotalSeats(30).build();
        Mockito.when(trainRepository.findById("4")).thenReturn(Optional.empty());
        assertThrows(TrainNoNotExistsException.class,
                () -> {
                    trainServiceImpl.getTrainByNo("4");
                });
    }

    @Test
    void deleteTrainByNo() throws TrainNoNotExistsException {
        Train t= Train.builder()
                .trainNo("1").trainName("Vande Bharath").totalKms(18).
                acCoaches(5).acCoachTotalSeats(17).sleeperCoaches(5).
                sleeperCoachTotalSeats(6).generalCoaches(25).generalCoacheTotalSeats(30).build();

        Mockito.when(trainRepository.findById("1")).thenReturn(Optional.of(t));
        assertEquals("Train deleted successfully",trainServiceImpl.deleteTrainByNo("1"));
    }

    }



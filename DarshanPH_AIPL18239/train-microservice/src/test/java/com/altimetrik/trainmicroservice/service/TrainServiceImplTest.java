package com.altimetrik.trainmicroservice.service;

import com.altimetrik.trainmicroservice.exception.TrainIdNotExistsException;
import com.altimetrik.trainmicroservice.model.Train;
import com.altimetrik.trainmicroservice.repository.TrainRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.Optional;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TrainServiceImplTest {
    @Mock
    private TrainRepository trainRepository;
    @InjectMocks
    private TrainServiceImpl trainServiceImpl;

    @Test
    void addTrainTest() throws Exception {
        Train t1=Train.builder()
                .trainId(1)
                .trainNumber(123)
                .trainName("wadeyar express")
                .acCoaches(15)
                .acCoachTotalSeats(100)
                .sleeperCoaches(20)
                .sleeperCoachTotalSeats(200)
                .generalCoaches(25)
                .generalCoachTotalSeats(300)
                .totalKms(150)
                .build();
        when(trainRepository.save(t1)).thenReturn(t1);
        assertEquals(t1,trainServiceImpl.addTrain(t1));

    }

    @Test
    void getAllTrain() {
        Train t1=Train.builder()
                .trainId(1)
                .trainNumber(123)
                .trainName("wadeyar express")
                .acCoaches(15)
                .acCoachTotalSeats(100)
                .sleeperCoaches(20)
                .sleeperCoachTotalSeats(200)
                .generalCoaches(25)
                .generalCoachTotalSeats(300)
                .totalKms(150)
                .build();
        Train t2=Train.builder()
                .trainId(2)
                .trainNumber(124)
                .trainName("wadeyar express")
                .acCoaches(15)
                .acCoachTotalSeats(100)
                .sleeperCoaches(20)
                .sleeperCoachTotalSeats(200)
                .generalCoaches(25)
                .generalCoachTotalSeats(300)
                .totalKms(150)
                .build();
        when(trainRepository.findAll()).thenReturn(Arrays.asList(t1,t2));
        assertEquals(2,trainServiceImpl.getAllTrain().size());
    }

    @Test
    void getTrainById() throws TrainIdNotExistsException {
        Train t1=Train.builder()
                .trainId(1)
                .trainNumber(123)
                .trainName("wadeyar express")
                .acCoaches(15)
                .acCoachTotalSeats(100)
                .sleeperCoaches(20)
                .sleeperCoachTotalSeats(200)
                .generalCoaches(25)
                .generalCoachTotalSeats(300)
                .totalKms(150)
                .build();
        when(trainRepository.findById(1)).thenReturn(Optional.of(t1));
        assertEquals(1,trainServiceImpl.getTrainById(1).getTrainId());

    }

    @Test
    void updateTrain() throws Exception {
        Train t1=Train.builder()
                .trainId(1)
                .trainNumber(123)
                .trainName("wadeyar express")
                .acCoaches(15)
                .acCoachTotalSeats(100)
                .sleeperCoaches(20)
                .sleeperCoachTotalSeats(200)
                .generalCoaches(25)
                .generalCoachTotalSeats(300)
                .totalKms(150)
                .build();
        when(trainRepository.findById(t1.getTrainId())).thenReturn(Optional.of(t1));
        when(trainRepository.save(t1)).thenReturn(t1);
        assertEquals(t1,trainServiceImpl.updateTrain(t1));

    }

    @Test
    void deleteTrainById() throws TrainIdNotExistsException {
        Train t1=Train.builder()
                .trainId(1)
                .trainNumber(123)
                .trainName("wadeyar express")
                .acCoaches(15)
                .acCoachTotalSeats(100)
                .sleeperCoaches(20)
                .sleeperCoachTotalSeats(200)
                .generalCoaches(25)
                .generalCoachTotalSeats(300)
                .totalKms(150)
                .build();;
        when(trainRepository.findById(t1.getTrainId())).thenReturn(Optional.of(t1));
//        when(trainRepository.deleteById(t1.getTrainId())).thenReturn(Optional.of(t1));
        String result = trainServiceImpl.deleteTrainById(t1.getTrainId());
        assertEquals("Train deleted successfully", result);
    }
}
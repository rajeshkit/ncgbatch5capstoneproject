package com.altimetrik.trainbooking.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.altimetrik.trainbooking.Repository.TrainRepository;
import com.altimetrik.trainbooking.exception.NoSuchElementException;
import com.altimetrik.trainbooking.modle.Train;
import com.altimetrik.trainbooking.service.TrainService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TrainController.class)
public class TrainControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TrainService trainService;
    @Autowired
    private ObjectMapper objectMapper;
    @Mock
    private TrainRepository trainRepository;


@Test
void addTrain() throws Exception {
    Train t1 = Train.builder()
            .trainNumber(100).trainName("Water Bootle").acCoaches(23).totalKms(34).acCoachTotalSeats(76)
            .sleeperCoaches(78).sleeperCoachTotalSeats(678).generalCoaches(87).generalCoacheTotalSeats(67).build();

    String trainJson = objectMapper.writeValueAsString(t1);

    when(trainService.addTrain(Mockito.any(Train.class))).thenReturn(t1);

    mockMvc.perform(post("/train-api/train")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(trainJson))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.trainNumber").value(100));
}


    @Test
    void getAllTrain() throws Exception {
        Train t1 = Train.builder()
                .trainNumber(100).trainName("Water Bootle").acCoaches(23).totalKms(34).acCoachTotalSeats(76)
                .sleeperCoaches(78).sleeperCoachTotalSeats(678).generalCoaches(87).generalCoacheTotalSeats(67).build();
        Train t2 = Train.builder()
                .trainNumber(101).trainName("shatabhi Express").acCoaches(33).totalKms(34).acCoachTotalSeats(56)
                .sleeperCoaches(98).sleeperCoachTotalSeats(78).generalCoaches(77).generalCoacheTotalSeats(87).build();
        when(trainService.getAllTrain()).thenReturn(Arrays.asList(t1,t2));
        mockMvc.perform(get("/train-api/train"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
    @Test
    void getTrainByNumber() throws Exception {
        Train t1=Train.builder()
                .trainNumber(123)
                .trainName("wadeyar express")
                .acCoaches(15)
                .acCoachTotalSeats(100)
                .sleeperCoaches(20)
                .sleeperCoachTotalSeats(200)
                .generalCoaches(25)
                .generalCoacheTotalSeats(300)
                .totalKms(150)
                .build();
        Mockito.when(trainService.getTrainByNumber(123)).thenReturn(t1);
        mockMvc.perform(MockMvcRequestBuilders.get("/train-api/train/{id}",123))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(123));
    }

    @Test
    void updatingTrain() throws Exception {
        Train updatedTrain = Train.builder()
                .trainNumber(456)
                .trainName("Updated Express")
                .acCoaches(18)
                .acCoachTotalSeats(120)
                .sleeperCoaches(25)
                .sleeperCoachTotalSeats(250)
                .generalCoaches(30)
                .generalCoacheTotalSeats(350)
                .totalKms(200)
                .build();

        Mockito.when(trainService.updateTrain(updatedTrain)).thenReturn(updatedTrain);

        mockMvc.perform(MockMvcRequestBuilders.put("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedTrain)))
                .andDo(print())
                .andExpect(status().isOk()) // Update the expected status code
                .andExpect(jsonPath("$.trainNumber").value(456)); // Update to the actual updatedTrain's trainNumber
    }



    @Test
    void deleteTrainByNumber() throws Exception {
        int trainNumber = 2;
        Mockito.when(trainService.deleteTrainByNumber(trainNumber)).thenReturn("Train does not exist");
        mockMvc.perform(delete("/train-api/train/{id}", trainNumber))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Train does not exist"));
    }
}

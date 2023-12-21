package com.altimetrik.trainmicroservice.controller;

import com.altimetrik.trainmicroservice.model.Train;
import com.altimetrik.trainmicroservice.service.TrainService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TrainController.class)
class TrainControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TrainService trainService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addTrain() throws Exception{

        Train t= Train.builder()
                .trainNumber(100)
                .trainName("Super Express")
                .totalKms(200)
                .acCoaches(15)
                .acCoachTotalSeats(20)
                .sleeperCoaches(8)
                .sleeperCoachTotalSeats(12)
                .generalCoaches(20)
                .generalCoachTotalSeats(28).build();

        Mockito.when(trainService.addTrain(t)).thenReturn(t);
        mockMvc.perform(post("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(t)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(100));
    }

    @Test
    void getAllTrains() throws Exception {

        Train t1= Train.builder()
                .trainNumber(20)
                .trainName("Super Express")
                .totalKms(100)
                .acCoaches(15)
                .acCoachTotalSeats(20)
                .sleeperCoaches(8)
                .sleeperCoachTotalSeats(12)
                .generalCoaches(20)
                .generalCoachTotalSeats(28).build();

        Train t2= Train.builder()
                .trainNumber(21)
                .trainName("Express123")
                .totalKms(200)
                .acCoaches(15)
                .acCoachTotalSeats(20)
                .sleeperCoaches(8)
                .sleeperCoachTotalSeats(10)
                .generalCoaches(18)
                .generalCoachTotalSeats(26).build();

        Mockito.when(trainService.getAllTrains()).thenReturn(Arrays.asList(t1,t2));
        mockMvc.perform(get("/train-api/train/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getTrainByNumber() throws Exception {

        Train t= Train.builder()
                .trainNumber(100)
                .trainName("Express123")
                .totalKms(200)
                .acCoaches(15)
                .acCoachTotalSeats(20)
                .sleeperCoaches(8)
                .sleeperCoachTotalSeats(12)
                .generalCoaches(20)
                .generalCoachTotalSeats(28).build();

        Mockito.when(trainService.getTrainByNumber(100)).thenReturn(t);

        mockMvc.perform(get("/train-api/train/{trainNumber}", 100))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(100));
    }

    @Test
    void updateTrain() throws Exception {

        Train t = Train.builder()
                .trainNumber(100)
                .trainName("Super Express")
                .totalKms(200)
                .acCoaches(15)
                .acCoachTotalSeats(20)
                .sleeperCoaches(8)
                .sleeperCoachTotalSeats(12)
                .generalCoaches(20)
                .generalCoachTotalSeats(28).build();

        Mockito.when(trainService.updateTrain(t)).thenReturn(t);
        mockMvc.perform(put("/train-api/train/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(t)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainName").value("Super Express"));
    }

    @Test
    void deleteTrainByNumber() throws Exception{

        int trainNumber = 100;

        Mockito.when(trainService.deleteTrainByNumber(trainNumber)).thenReturn("Train deleted successfully");

        mockMvc.perform(delete("/train-api/train/{trainNumber}", trainNumber))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string("Train deleted successfully"));

    }
}
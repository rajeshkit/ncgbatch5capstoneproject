package com.altimetrik.train.controller;

import com.altimetrik.train.model.Train;
import com.altimetrik.train.service.TrainService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;

import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import java.util.Arrays;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
    void addTrains() throws Exception {
        Train train2 = Train.builder().trainNumber(100).trainName("express two").totalKms(600)
                .acCoaches(6).acCoachTotalSeats(60).sleeperCoaches(9).sleeperCoachTotalSeats(150)
                .generalCoaches(8).generalCoacheTotalSeats(160).build();
        Mockito.when(trainService.addTrain(train2)).thenReturn(train2);
        mockMvc.perform(post("/train/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(train2)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(100));
    }
    @Test
    void getAllTrains() throws Exception {
        Train train = Train.builder().trainNumber(100).trainName("express two").totalKms(600)
                .acCoaches(6).acCoachTotalSeats(60).sleeperCoaches(9).sleeperCoachTotalSeats(150)
                .generalCoaches(8).generalCoacheTotalSeats(160).build();
        Train train2 = Train.builder().trainNumber(101).trainName("express one").totalKms(500)
                .acCoaches(5).acCoachTotalSeats(50).sleeperCoaches(8).sleeperCoachTotalSeats(200)
                .generalCoaches(8).generalCoacheTotalSeats(160).build();
        Mockito.when(trainService.getAllTrains()).thenReturn(Arrays.asList(train,train2));
        mockMvc.perform(get("/train/train"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
    @Test
    void getTrainByNumber() throws Exception {
        int trainNumber = 100;

        Train train = Train.builder()
                .trainNumber(trainNumber)
                .trainName("Express One")
                .totalKms(600)
                .acCoaches(6)
                .acCoachTotalSeats(60)
                .sleeperCoaches(9)
                .sleeperCoachTotalSeats(150)
                .generalCoaches(8)
                .generalCoacheTotalSeats(160)
                .build();

        when(trainService.getTrainByNumber(trainNumber)).thenReturn(train);

        mockMvc.perform(get("/train/{trainNumber}", trainNumber))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.trainNumber").value(trainNumber))
                .andExpect(jsonPath("$.trainName").value("Express One"))
                .andExpect(jsonPath("$.totalKms").value(600))
                .andExpect(jsonPath("$.acCoaches").value(6))
                .andExpect(jsonPath("$.acCoachTotalSeats").value(60))
                .andExpect(jsonPath("$.sleeperCoaches").value(9))
                .andExpect(jsonPath("$.sleeperCoachTotalSeats").value(150))
                .andExpect(jsonPath("$.generalCoaches").value(8))
                .andExpect(jsonPath("$.generalCoacheTotalSeats").value(160));
    }

    @Test
    void updateTrain() throws Exception {
        Train updatedTrain = Train.builder().trainNumber(100).trainName("chennai express").totalKms(700)
                .acCoaches(7).acCoachTotalSeats(70).sleeperCoaches(10).sleeperCoachTotalSeats(180)
                .generalCoaches(9).generalCoacheTotalSeats(180).build();

        Mockito.when(trainService.updateTrain(Mockito.any(Train.class))).thenReturn(updatedTrain);

        mockMvc.perform(put("/train/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(updatedTrain)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainName").value("chennai express"))
                .andExpect(jsonPath("$.totalKms").value(700));
    }

    @Test
    void deleteTrainByNumber() throws Exception {
        int trainNumber = 100;

        Mockito.when(trainService.deleteTrainByNumber(trainNumber)).thenReturn("Train deleted successfully");

        mockMvc.perform(delete("/train/train/{trainNumber}", trainNumber))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Train deleted successfully"));
    }



}


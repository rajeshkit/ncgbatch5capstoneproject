package com.altimetrik.trainbookingsystem.controller;


import com.altimetrik.trainbookingsystem.model.Train;
import com.altimetrik.trainbookingsystem.service.TrainService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;



import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Test
    void addTrain() throws Exception {
        Train t1 = Train.builder().trainNo("1").trainName("Vande Bharath").totalKms(18).
                acCoaches(5).acCoachTotalSeats(17).sleeperCoaches(5).
                sleeperCoachTotalSeats(6).generalCoaches(25).generalCoacheTotalSeats(30).build();

        Mockito.when(trainService.addTrain(t1)).thenReturn(t1);
        mockMvc.perform(post("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(t1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNo").value(1));

    }

    @Test
    void getAlltrain() throws Exception {
        Train t1 = Train.builder()
                .trainNo("1").trainName("Vande Bharath").totalKms(18).
                acCoaches(5).acCoachTotalSeats(17).sleeperCoaches(5).
                sleeperCoachTotalSeats(6).generalCoaches(25).generalCoacheTotalSeats(30).build();
        Train t2 = Train.builder()
                .trainNo("2").trainName("Shatabdi Express").totalKms(23).
                acCoaches(67).acCoachTotalSeats(80).sleeperCoaches(42).
                sleeperCoachTotalSeats(56).generalCoaches(67).generalCoacheTotalSeats(87).build();

        Mockito.when(trainService.getAllTrain()).thenReturn(Arrays.asList(t1, t2));
        mockMvc.perform(get("/train-api/train"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getTrainByNo() {
    }

    @Test
    void updateTrain() {
    }

    @Test
    void deleteTrainByNo() throws Exception {
        String trainNo = "1";

        Mockito.when(trainService.deleteTrainByNo(trainNo)).thenReturn("Train deleted successfully");

        mockMvc.perform(delete("/train-api/train/{no}", trainNo))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string("Train deleted successfully"));

    }
}

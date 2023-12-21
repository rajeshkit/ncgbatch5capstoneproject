package com.altimetrik.trainmicroservices.controller;

import com.altimetrik.trainmicroservices.model.Train;
import com.altimetrik.trainmicroservices.service.TrainService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Nested
@WebMvcTest(TrainController.class)
class TrainControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainService trainService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddTrain() throws Exception {
        Train trainToAdd = Train.builder().trainName("Express Train").totalKms(200.0).acCoaches(10).acCoachTotalSeats(15).sleeperCoaches(5).sleeperCoachTotalSeats(10).generalCoaches(8).generalCoachTotalSeats(20).build();

        when(trainService.addTrain(Mockito.any(Train.class))).thenReturn(trainToAdd);

        mockMvc.perform(post("/train-api/train").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(trainToAdd))).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andExpect(jsonPath("$.trainName").value("Express Train"));
    }

    @Test
    void testGetAllTrains() throws Exception {
        List<Train> trainList = List.of();

        when(trainService.getAllTrains()).thenReturn(trainList);

        mockMvc.perform(get("/train-api/train")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(trainList.size())));
    }

    @Test
    void testGetTrainByTrainNumber() throws Exception {
        Train existingTrain = Train.builder().trainNumber(101).trainName("Express Train").totalKms(200.0).acCoaches(10).acCoachTotalSeats(15).sleeperCoaches(5).sleeperCoachTotalSeats(10).generalCoaches(8).generalCoachTotalSeats(20).build();

        when(trainService.getTrainByTrainNumber(101)).thenReturn(existingTrain);

        mockMvc.perform(get("/train-api/train/101")).andExpect(status().isOk()).andExpect(jsonPath("$.trainName").value("Express Train"));
    }

    @Test
    void testUpdateTrain() throws Exception {
        Train trainToUpdate = Train.builder().trainNumber(1).trainName("Express").totalKms(500.0).acCoaches(2).acCoachTotalSeats(50).sleeperCoaches(5).sleeperCoachTotalSeats(200).generalCoaches(10).generalCoachTotalSeats(500).build();
        when(trainService.updateTrain(Mockito.any(Train.class))).thenReturn(trainToUpdate);
        mockMvc.perform(put("/train-api/train").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(trainToUpdate))).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andExpect(jsonPath("$.trainNumber").value(1));

    }

    @Test
    void testDeleteTrainByTrainNumber() throws Exception {
        mockMvc.perform(delete("/train-api/train/101")).andExpect(status().isOk());
    }
}

package com.altimetrik.train.trainrestapi.controller;

import com.altimetrik.train.trainrestapi.model.Train;
import com.altimetrik.train.trainrestapi.service.TrainService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TrainController.class)
class TrainControllerTest {

    @MockBean
    private TrainService trainService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addTrain() throws Exception {
        Train train = Train.builder()
                .trainNumber("1")
                .trainName("BangloreExpress")
                .acCoaches("Available")
                .acCoachesTotalSeats(200)
                .generalCoaches("Available")
                .generalCoachesTotalSeats(420)
                .build();

        // Convert the Train object to JSON
        String jsonTrain = objectMapper.writeValueAsString(train);

        // Perform the POST request and expect a 201 Created status
        mockMvc.perform(MockMvcRequestBuilders.post("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonTrain))
                .andDo(MockMvcResultHandlers.print()) // Print the request and response for debugging
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", "http://localhost/train-api/train/1"));
    }


    @Test
    void getAllTrains() throws Exception {

        Train train = Train.builder()
                .trainNumber("1")
                .trainName("BangloreExpress")
                .acCoaches("Available")
                .acCoachesTotalSeats(200)
                .generalCoaches("Available")
                .generalCoachesTotalSeats(420)
                .build();

        Train train1 = Train.builder()
                .trainNumber("2")
                .trainName("Al-Arabian Express")
                .acCoaches("Available")
                .acCoachesTotalSeats(240)
                .generalCoaches("Available")
                .generalCoachesTotalSeats(420)
                .build();

        String jsonTrain = objectMapper.writeValueAsString(train);
        Mockito.when(trainService.getAllTrains()).thenReturn(Arrays.asList(train, train1));

        mockMvc.perform(get("/train-api/train"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

    }

    @Test
    void getTrainByTrainID() throws Exception {

        Train train = Train.builder()
                .trainNumber("1")
                .trainName("Panchavati Express")
                .acCoaches("Available")
                .acCoachesTotalSeats(220)
                .generalCoaches("Available")
                .generalCoachesTotalSeats(440)
                .build();

        Mockito.when(trainService.getTrainByTrainID(train.getTrainNumber())).thenReturn(train);

        mockMvc.perform(get("/train-api/train/{id}", train.getTrainNumber()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(train.getTrainNumber()));

    }

    @Test
    void updateTrain() throws Exception {
        Train train = Train.builder()
                .trainNumber("1")
                .trainName("Panchavati Express")
                .acCoaches("Available")
                .acCoachesTotalSeats(220)
                .generalCoaches("Available")
                .generalCoachesTotalSeats(440)
                .build();

        Mockito.when(trainService.updateTrain(train, train.getTrainNumber())).thenReturn(train);

        mockMvc.perform(put("/train-api/train/{id}", train.getTrainNumber())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(train)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(train.getTrainNumber()));
    }

    @Test
    void deleteTrainByTrainID() throws Exception {

        Train train = Train.builder()
                .trainNumber("1")
                .trainName("Panchavati Express")
                .acCoaches("Available")
                .acCoachesTotalSeats(220)
                .generalCoaches("Available")
                .generalCoachesTotalSeats(440)
                .build();

        Mockito.when(trainService.deleteTrainByTrainID(train.getTrainNumber())).thenReturn(String.valueOf(train));

        mockMvc.perform(delete("/train-api/train/{id}", train.getTrainNumber()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

    }
}
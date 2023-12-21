package com.train.trainmicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.train.trainmicroservice.model.Train;
import com.train.trainmicroservice.repository.TrainRepository;
import com.train.trainmicroservice.service.TrainService;
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
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@WebMvcTest(TrainController.class)
public class TrainControllerTest {

    @MockBean
    private TrainService trainService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddTrain() throws Exception{
        Train train = Train.builder().trainNumber(1).trainName("Rajasthani Expressss").totalKms(1490)
                .acCoaches(3).acCoachTotalSeats(120)
                .sleeperCoaches(5).sleeperCoachTotalSeats(200)
                .generalCoaches(7).generalCoachesTotalSeats(280).build();
        Mockito.when(trainService.addTrain(train)).thenReturn(train);

        mockMvc.perform(MockMvcRequestBuilders.post("/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(train)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.trainNumber").exists());
    }
    @Test
    public void testGetAllTrains() throws Exception {
        Train train1 = Train.builder().trainNumber(1).trainName("Rajasthani Expressss").totalKms(1490)
                .acCoaches(3).acCoachTotalSeats(120)
                .sleeperCoaches(5).sleeperCoachTotalSeats(200)
                .generalCoaches(7).generalCoachesTotalSeats(280).build();
        Train train2 = Train.builder().trainNumber(1).trainName("Haryanvi Expressss").totalKms(1000)
                .acCoaches(3).acCoachTotalSeats(120)
                .sleeperCoaches(5).sleeperCoachTotalSeats(200)
                .generalCoaches(7).generalCoachesTotalSeats(280).build();
        Mockito.when(trainService.getAllTrains()).thenReturn(Arrays.asList(train1,train2));

        mockMvc.perform(MockMvcRequestBuilders.get("/train"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }
    @Test
    public void testGetTrainDetailsById() throws Exception {
        int trainId = 1;
        Train train = Train.builder().trainNumber(1).trainName("Rajasthani Expressss").totalKms(1490)
                .acCoaches(3).acCoachTotalSeats(120)
                .sleeperCoaches(5).sleeperCoachTotalSeats(200)
                .generalCoaches(7).generalCoachesTotalSeats(280).build();
        Mockito.when(trainService.getTrainByTrainNo(trainId)).thenReturn(train);

        mockMvc.perform(MockMvcRequestBuilders.get("/train/{id}", trainId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.trainNumber").value(trainId));
    }

    @Test
    public void testUpdateTrainDetails() throws Exception {
        Train train = Train.builder().trainNumber(1).trainName("Rajasthani Expressss").totalKms(1090)
                .acCoaches(3).acCoachTotalSeats(120)
                .sleeperCoaches(5).sleeperCoachTotalSeats(200)
                .generalCoaches(7).generalCoachesTotalSeats(280).build();
        Mockito.when(trainService.updateTrain(train)).thenReturn(train);

        mockMvc.perform(MockMvcRequestBuilders.put("/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(train)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.trainNumber").exists());
    }



}
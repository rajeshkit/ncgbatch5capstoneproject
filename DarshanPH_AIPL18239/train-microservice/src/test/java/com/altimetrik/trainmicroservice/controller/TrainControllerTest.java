package com.altimetrik.trainmicroservice.controller;

import com.altimetrik.trainmicroservice.controller.TrainController;
import com.altimetrik.trainmicroservice.exception.TrainIdNotExistsException;
import com.altimetrik.trainmicroservice.model.Train;
import com.altimetrik.trainmicroservice.service.TrainService;
import com.fasterxml.jackson.core.JsonProcessingException;
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

//import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Optional;


import static java.lang.reflect.Array.get;
import static java.util.Optional.*;
import static java.util.Optional.of;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;

@WebMvcTest(TrainController.class)
public class TrainControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private TrainService trainService;
    @Test
    void testAddTrain() throws Exception{
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
        when(trainService.addTrain(t1)).thenReturn(t1);
        mockMvc.perform(post("/Train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(t1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainId").value(1));;

    }

    @Test
    void testGetAllTrain() throws Exception {
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
                .trainNumber(132)
                .trainName("wadeyar express")
                .acCoaches(15)
                .acCoachTotalSeats(100)
                .sleeperCoaches(20)
                .sleeperCoachTotalSeats(200)
                .generalCoaches(25)
                .generalCoachTotalSeats(300)
                .totalKms(150)
                .build();
        when(trainService.getAllTrain()).thenReturn(Arrays.asList(t1,t2));
        mockMvc.perform(MockMvcRequestBuilders.get("/Train-api/train"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getTrainById() throws Exception {
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
        Mockito.when(trainService.getTrainById(1)).thenReturn(t1);
        mockMvc.perform(MockMvcRequestBuilders.get("/Train-api/train/{id}",1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainId").value(1));
    }

    @Test
    void updateTrain() throws Exception {
        Train updatedTrain = Train.builder()
            .trainId(1)
            .trainNumber(456)
            .trainName("Updated Express")
            .acCoaches(18)
            .acCoachTotalSeats(120)
            .sleeperCoaches(25)
            .sleeperCoachTotalSeats(250)
            .generalCoaches(30)
            .generalCoachTotalSeats(350)
            .totalKms(200)
            .build();

        Mockito.when(trainService.updateTrain(updatedTrain)).thenReturn(updatedTrain);
        mockMvc.perform(MockMvcRequestBuilders.put("/Train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedTrain)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainId").value(1));
    }

    @Test
    void deleteTrainByIdExistingId() throws Exception {
        int trainId = 1;
        Mockito.when(trainService.deleteTrainById(trainId)).thenReturn("Train deleted successfully");
        mockMvc.perform(delete("/Train-api/train/{id}", trainId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Train deleted successfully"));
    }
    @Test
    void deleteTrainByIdNonExistingId() throws Exception {
        int trainId = 2;
        Mockito.when(trainService.deleteTrainById(trainId)).thenReturn("Train does not exist");
        mockMvc.perform(delete("/Train-api/train/{id}", trainId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Train does not exist"));
    }
}

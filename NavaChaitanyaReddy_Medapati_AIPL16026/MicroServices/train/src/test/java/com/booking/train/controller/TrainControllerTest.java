package com.booking.train.controller;

import com.booking.train.exception.TrainNumberNotExistsException;
import com.booking.train.model.TrainResources;
import com.booking.train.service.TrainService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.hamcrest.core.StringContains.containsString;


import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TrainController.class)
class TrainControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private TrainService trainService;

    @Test
    void addTrainResources() throws Exception {

        TrainResources trainResources=TrainResources.builder()
                .trainNumber(17644l).trainName("Circar").totalKms(780.0)
                .acCoaches(3).acCoachTotalSeats(168).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(10).sleeperCoachTotalSeats(600).build();

        Mockito.when(trainService.addTrainResources(trainResources)).thenReturn(trainResources);

        mockMvc.perform(post("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(trainResources)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(17644l));


        //assertEquals(trainResources,trainService.addTrainResources(trainResources));
    }

    @Test
    void getAllTrainResources() throws Exception {
        TrainResources trainResources=TrainResources.builder()
                .trainNumber(17644l).trainName("Circar").totalKms(780.0)
                .acCoaches(3).acCoachTotalSeats(168).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(10).sleeperCoachTotalSeats(600).build();

        TrainResources trainResources1=TrainResources.builder()
                .trainNumber(12737l).trainName("Gowthami").totalKms(550.0)
                .acCoaches(3).acCoachTotalSeats(168).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(10).sleeperCoachTotalSeats(600).build();

        Mockito.when(trainService.getAllTrainResources()).thenReturn(Arrays.asList(trainResources,trainResources1));

        mockMvc.perform(get("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));;

//        assertEquals(2,trainService.getAllTrainResources().size());
    }

    @Test
    void getTrainResourcesByTrainNumber() throws Exception {
        TrainResources trainResources=TrainResources.builder()
                .trainNumber(17644l).trainName("Circar").totalKms(780.0)
                .acCoaches(3).acCoachTotalSeats(168).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(10).sleeperCoachTotalSeats(600).build();

        Mockito.when(trainService.getAllTrainResourcesById(17644l)).thenReturn(trainResources);
        mockMvc.perform(get("/train-api/train/{trainNumber}",trainResources.getTrainNumber())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(trainResources)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(17644l));;

        //assertEquals(trainResources.getTrainNumber(),trainService.getAllTrainResourcesById(trainResources.getTrainNumber()).getTrainNumber());

    }

    @Test
    void getTrainResourcesByTrainNumberWithException() throws Exception {
        TrainResources trainResources=TrainResources.builder()
                .trainNumber(17644l).trainName("Circar").totalKms(780.0)
                .acCoaches(3).acCoachTotalSeats(168).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(10).sleeperCoachTotalSeats(600).build();

        Mockito.when(trainService.getAllTrainResourcesById(100l)).thenThrow(new TrainNumberNotExistsException("Train Number Not Found in db table..!"));
        mockMvc.perform(get("/train-api/train/{trainNumber}",100l))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Train Number Not Found in db table..!"));
    }

    @Test
    void updateTrainResource() throws Exception {
        TrainResources trainResources=TrainResources.builder()
                .trainNumber(17644l).trainName("Circar").totalKms(780.0)
                .acCoaches(3).acCoachTotalSeats(168).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(10).sleeperCoachTotalSeats(600).build();

        Mockito.when(trainService.updateTrainResource(trainResources)).thenReturn(trainResources);
        mockMvc.perform(put("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(trainResources)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(17644l));

    }

    @Test
    void updateTrainResourceWithException() throws Exception {
        TrainResources trainResources=TrainResources.builder()
                .trainNumber(17644l).trainName("Circar").totalKms(780.0)
                .acCoaches(3).acCoachTotalSeats(168).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(10).sleeperCoachTotalSeats(600).build();

        Mockito.when(trainService.updateTrainResource(trainResources)).thenThrow(new TrainNumberNotExistsException("Train Number not found in db..!"));
        mockMvc.perform(put("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(trainResources)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Train Number not found in db..!"));

    }


    @Test
    void deleteResourceById() throws Exception {
        TrainResources trainResources=TrainResources.builder()
                .trainNumber(17644l).trainName("Circar").totalKms(780.0)
                .acCoaches(3).acCoachTotalSeats(168).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(10).sleeperCoachTotalSeats(600).build();

        Mockito.when(trainService.deleteTrainResourceByTrainNumber(100l)).thenReturn("Deleted Successfully Based on Train Number");
        mockMvc.perform(delete("/train-api/train/{trainNumber}",100l))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Deleted Successfully Based on Train Number")));

    }

    @Test
    void deleteTrainResourcesByTrainNumberWithException() throws Exception {
        TrainResources trainResources=TrainResources.builder()
                .trainNumber(17644l).trainName("Circar").totalKms(780.0)
                .acCoaches(3).acCoachTotalSeats(168).generalCoaches(1).generalCoachesTotalSeats(60)
                .sleeperCoaches(10).sleeperCoachTotalSeats(600).build();

        Mockito.when(trainService.deleteTrainResourceByTrainNumber(1010l)).thenThrow(new TrainNumberNotExistsException("Train Number not found in db..!"));
        mockMvc.perform(delete("/train-api/train/{trainNumber}",1010l))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Train Number not found in db..!"));
    }
}
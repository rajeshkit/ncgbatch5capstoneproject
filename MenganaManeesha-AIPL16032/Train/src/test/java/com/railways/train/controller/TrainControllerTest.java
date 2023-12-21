package com.railways.train.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.railways.train.exception.TrainNumberNotFoundException;
import com.railways.train.model.Train;
import com.railways.train.service.TrainService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

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
    void addTrain() throws Exception {
        Train t= Train.builder().trainNumber(123).trainName("ABC").totalKms(100).acCoaches(10).acCoachTotalSeats(100).sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(8).generalCoachTotalSeats(80).build();
        Mockito.when(trainService.addTrain(t)).thenReturn(t);
        mockMvc.perform(post("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(t)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(123));

    }

    @Test
    void getAllTrains() throws Exception {
        Train t1= Train.builder().trainNumber(456).trainName("DEF").totalKms(200).acCoaches(9).acCoachTotalSeats(100).sleeperCoaches(4).sleeperCoachTotalSeats(50).generalCoaches(6).generalCoachTotalSeats(80).build();
        Train t2= Train.builder().trainNumber(123).trainName("ABC").totalKms(100).acCoaches(10).acCoachTotalSeats(100).sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(8).generalCoachTotalSeats(80).build();
        Mockito.when(trainService.getAllTrains()).thenReturn(Arrays.asList(t1,t2));
        mockMvc.perform(get("/train-api/train"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getTrainById() throws Exception {
        Train t= Train.builder().trainNumber(123).trainName("ABC").totalKms(100).acCoaches(10).acCoachTotalSeats(100).sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(8).generalCoachTotalSeats(80).build();
        Mockito.when(trainService.getTrainById(123)).thenReturn(t);
        mockMvc.perform(get("/train-api/train/{id}",123))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(123));
    }
    @Test
    void getTrainByIdWithException() throws Exception {
        Train t= Train.builder().trainNumber(123).trainName("ABC").totalKms(100).acCoaches(10).acCoachTotalSeats(100).sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(8).generalCoachTotalSeats(80).build();
        Mockito.when(trainService.getTrainById(456)).thenThrow(new TrainNumberNotFoundException("Train not found"));
        mockMvc.perform(get("/train-api/train/{id}",456))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                . andExpect(jsonPath("$.message").value("Train not found"));;
    }

    @Test
    void updateTrain() throws Exception {
        Train t= Train.builder().trainNumber(123).trainName("ABC").totalKms(100).acCoaches(10).acCoachTotalSeats(100).sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(8).generalCoachTotalSeats(80).build();
        Mockito.when(trainService.updateTrain(t)).thenReturn(t);
        mockMvc.perform(put("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(t)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(123));
    }
    @Test
    void updateTrainWithException() throws Exception {
        Train t= Train.builder().trainNumber(567).trainName("ABC").totalKms(100).acCoaches(10).acCoachTotalSeats(100).sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(8).generalCoachTotalSeats(80).build();
        Mockito.when(trainService.updateTrain(t)).thenThrow(new TrainNumberNotFoundException("Train not found"));
        mockMvc.perform(put("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(t)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Train not found"));;
    }

    @Test
    void deleteTrainById() throws Exception {
        Train t= Train.builder().trainNumber(123).trainName("ABC").totalKms(100).acCoaches(10).acCoachTotalSeats(100).sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(8).generalCoachTotalSeats(80).build();
        Mockito.when(trainService.deleteTrainById(123)).thenReturn("Train Deleted Successfully");
        mockMvc.perform(delete("/train-api/train/{id}",123))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Train Deleted Successfully"));
    }
    @Test
    void deleteTrainByIdWithException() throws Exception {
        Train t= Train.builder().trainNumber(123).trainName("ABC").totalKms(100).acCoaches(10).acCoachTotalSeats(100).sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(8).generalCoachTotalSeats(80).build();
        Mockito.when(trainService.deleteTrainById(456)).thenThrow(new TrainNumberNotFoundException("Train not found"));
        mockMvc.perform(delete("/train-api/train/{id}",456))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Train not found"));;
    }
}
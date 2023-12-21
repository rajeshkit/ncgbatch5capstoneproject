package com.train.trainmicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.train.trainmicroservice.exception.TrainNumberNotExistsException;
import com.train.trainmicroservice.model.Train;
import com.train.trainmicroservice.service.TrainService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.Optional;


import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TrainController.class)
class TrainControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainService trainService;
    @Autowired
    private ObjectMapper objectMapper;
    @InjectMocks
    private TrainController trainController;


    @Test
    void addTrain() throws Exception {
        Train train= Train.builder().trainNumber(1005).trainName("Kachiguda Express")
                .acCoaches(10).acCoachTotalSeats(300)
                .sleeperCoaches(20).sleeperCoachTotalSeats(500)
                .generalCoaches(40).generalCoachesTotalSeats(800).totalKms(1200)
                .build();
        Mockito.when(trainService.addTrain(train)).thenReturn(train);
        mockMvc.perform(post("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(train)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(1005));
    }

    @Test
    void getAllTrains() throws Exception {
        Train train= Train.builder().trainNumber(1005).trainName("Kachiguda Express")
                .acCoaches(10).acCoachTotalSeats(300)
                .sleeperCoaches(20).sleeperCoachTotalSeats(500)
                .generalCoaches(40).generalCoachesTotalSeats(800).totalKms(1200)
                .build();
        Train train1= Train.builder().trainNumber(1007).trainName("Mumbai Express")
                .acCoaches(20).acCoachTotalSeats(400)
                .sleeperCoaches(30).sleeperCoachTotalSeats(500)
                .generalCoaches(50).generalCoachesTotalSeats(1000).totalKms(1400)
                .build();
        Mockito.when(trainService.getAllTrains()).thenReturn(Arrays.asList(train,train1));
        assertEquals(2,trainService.getAllTrains().size());
        mockMvc.perform(get("/train-api/train"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }



    @Test
    void getTrainByTrainNo() throws Exception {
        Train train= Train.builder().trainNumber(1008).trainName("Mumbai Express")
                .acCoaches(8).acCoachTotalSeats(280)
                .sleeperCoaches(15).sleeperCoachTotalSeats(300)
                .generalCoaches(40).generalCoachesTotalSeats(800).totalKms(1500)
                .build();
        Mockito.when(trainService.getTrainByTrainNo(1008)).thenReturn(train);
        mockMvc.perform(get("/train-api/train/{trainNumber}",1008))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(1008));
    }

    @Test
    void getTrainByTrainNoWithException() throws Exception {
        Train train= Train.builder().trainNumber(1008).trainName("Mumbai Express")
                .acCoaches(8).acCoachTotalSeats(280)
                .sleeperCoaches(15).sleeperCoachTotalSeats(300)
                .generalCoaches(40).generalCoachesTotalSeats(800).totalKms(1500)
                .build();
        Mockito.when(trainService.getTrainByTrainNo(1010)).thenThrow(new TrainNumberNotExistsException("Train Number Not Exist"));
        mockMvc.perform(get("/train-api/train/{trainNumber}",1010))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Train Number Not Exist"));
    }

    @Test
    void updateTrain() throws Exception {
        Train train= Train.builder().trainNumber(1008).trainName("Mumbai Express")
                .acCoaches(8).acCoachTotalSeats(280)
                .sleeperCoaches(15).sleeperCoachTotalSeats(300)
                .generalCoaches(40).generalCoachesTotalSeats(800).totalKms(1500)
                .build();
        Mockito.when(trainService.updateTrain(train)).thenReturn(train);
        mockMvc.perform(put("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(train)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(1008));

    }

    @Test
    void updateTrainWithException() throws Exception {
        Train train= Train.builder().trainNumber(1008).trainName("Mumbai Express")
                .acCoaches(8).acCoachTotalSeats(280)
                .sleeperCoaches(15).sleeperCoachTotalSeats(300)
                .generalCoaches(40).generalCoachesTotalSeats(800).totalKms(1500)
                .build();
        Mockito.when(trainService.updateTrain(train)).thenThrow(new TrainNumberNotExistsException("Train Number Not Exist"));
        mockMvc.perform(put("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(train)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Train Number Not Exist"));

    }

    @Test
    void deleteTrainByTrainNo() throws Exception {
        Train train= Train.builder().trainNumber(1008).trainName("Mumbai Express")
                .acCoaches(8).acCoachTotalSeats(280)
                .sleeperCoaches(15).sleeperCoachTotalSeats(300)
                .generalCoaches(40).generalCoachesTotalSeats(800).totalKms(1500)
                .build();
        Mockito.when(trainService.deleteTrainByTrainNo(1008)).thenReturn("Train Deleted Successfully");
        mockMvc.perform(delete("/train-api/train/{trainNumber}",1008))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Train Deleted Successfully")));
    }

    @Test
    void deleteTrainByTrainNoWithException() throws Exception {
        Train train= Train.builder().trainNumber(1008).trainName("Mumbai Express")
                .acCoaches(8).acCoachTotalSeats(280)
                .sleeperCoaches(15).sleeperCoachTotalSeats(300)
                .generalCoaches(40).generalCoachesTotalSeats(800).totalKms(1500)
                .build();
        Mockito.when(trainService.deleteTrainByTrainNo(1010)).thenThrow(new TrainNumberNotExistsException("Train Number Not Exist"));
        mockMvc.perform(delete("/train-api/train/{trainNumber}",1010))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Train Number Not Exist"));
    }
}
package com.altimetrik.train.controller;

import com.altimetrik.train.exception.TrainNumberNotFoundException;
import com.altimetrik.train.model.Train;
import com.altimetrik.train.service.TrainService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    Train t1=Train.builder()
            .trainNo(10001).trainName("Charminar Express")
            .totalKms(700).acCoaches(10).acTotalSeats(720)
            .sleeperCoaches(10).sleeperTotalSeats(720)
            .generalCoaches(2).generalTotalSeats(144).build();
    Train t2=Train.builder()
            .trainNo(10002).trainName("Golconda Express")
            .totalKms(800).acCoaches(10).acTotalSeats(720)
            .sleeperCoaches(10).sleeperTotalSeats(720)
            .generalCoaches(2).generalTotalSeats(144).build();


    @Test
    void addTrain() throws Exception {
        Mockito.when(trainService.addTrain(t1)).thenReturn(t1);
        mockMvc.perform(post("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(t1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNo").value(10001));

    }

    @Test
    void viewAllTrains() throws Exception {
        Mockito.when(trainService.viewAllTrains()).thenReturn(Arrays.asList(t1,t2));
        assertEquals(2,trainService.viewAllTrains().size());
        mockMvc.perform(get("/train-api/train"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getTrainById() throws Exception {
        Mockito.when(trainService.getTrainById(10002)).thenReturn(t2);
        mockMvc.perform(get("/train-api/train/{trainNum}",10002))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNo").value(10002));
    }

    @Test
    void getTrainByIdWithException() throws Exception {
        Mockito.when(trainService.getTrainById(10001)).thenThrow(new TrainNumberNotFoundException("Train Number Not Found!"));
        mockMvc.perform(get("/train-api/train/{trainNo}",10001))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Train Number Not Found!"));
    }

    @Test
    void updateTrainById() throws Exception {
        Mockito.when(trainService.updateTrain(t2)).thenReturn(t2);
        mockMvc.perform(put("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(t2)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNo").value(10002));
    }

    @Test
    void updateTrainByIdWithException() throws Exception {
        Mockito.when(trainService.updateTrain(t2)).thenThrow(new TrainNumberNotFoundException("Train Number Not Found!"));
        mockMvc.perform(put("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(t2)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Train Number Not Found!"));
    }

    @Test
    void deleteTrainById() throws Exception {
        Mockito.when(trainService.deleteTrainById(10002)).thenReturn("Train Details Deleted Successfully");
        mockMvc.perform(delete("/train-api/train/{trainNumber}",10002))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Train Details Deleted Successfully")));
    }

    @Test
    void deleteTrainByIdWithException() throws Exception {
        Mockito.when(trainService.deleteTrainById(10007)).thenThrow(new TrainNumberNotFoundException("Train Number Not Found!"));
        mockMvc.perform(delete("/train-api/train/{trainNumber}",10007))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Train Number Not Found!"));
    }
}
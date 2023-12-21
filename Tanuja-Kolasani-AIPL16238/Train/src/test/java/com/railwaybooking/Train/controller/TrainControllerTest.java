package com.railwaybooking.Train.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.railwaybooking.Train.exception.TrainNumberNotExistException;
import com.railwaybooking.Train.model.TrainInfo;
import com.railwaybooking.Train.service.TrainService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.*;
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
    void addTrain() throws Exception{
        TrainInfo t= TrainInfo.builder().trainNumber(76542).trainName("Sarkar").totalKms(200).acCoaches(8).acCoachTotalSeats(80).sleeperCoaches(6).sleeperCoachTotalSeats(50).generalCoaches(4).generalCoachTotalSeats(80).build();
        Mockito.when(trainService.addTrain(t)).thenReturn(t);
       mockMvc.perform(post("/trainInfo-api/trainInfo")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writerWithDefaultPrettyPrinter()
                       .writeValueAsString(t)))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.trainNumber").value(76542));

    }
    @Test
    void getAllTrains() throws Exception{
        TrainInfo t1= TrainInfo.builder().trainNumber(456).trainName("Krishna").totalKms(200).acCoaches(9).acCoachTotalSeats(100).sleeperCoaches(4).sleeperCoachTotalSeats(50).generalCoaches(6).generalCoachTotalSeats(80).build();
        TrainInfo t2=TrainInfo.builder().trainNumber(76542).trainName("Sarkar").totalKms(200).acCoaches(8).acCoachTotalSeats(80).sleeperCoaches(6).sleeperCoachTotalSeats(50).generalCoaches(4).generalCoachTotalSeats(80).build();
        Mockito.when(trainService.getAllTrains()).thenReturn(Arrays.asList(t1,t2));
        mockMvc.perform(get("/trainInfo-api/trainInfo"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
    @Test
    void getTrainByNumber() throws Exception{
        TrainInfo t=TrainInfo.builder().trainNumber(76542).trainName("Sarkar").totalKms(200).acCoaches(8).acCoachTotalSeats(80).sleeperCoaches(6).sleeperCoachTotalSeats(50).generalCoaches(4).generalCoachTotalSeats(80).build();
        Mockito.when(trainService.getTrainByNumber(76542)).thenReturn(t);
        mockMvc.perform(get("/trainInfo-api/trainInfo/{number}",76542))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(76542));
    }

    @Test
    void getTrainByNumberWithException() throws Exception{
        TrainInfo t=TrainInfo.builder().trainNumber(76542).trainName("Sarkar").totalKms(200).acCoaches(8).acCoachTotalSeats(80).sleeperCoaches(6).sleeperCoachTotalSeats(50).generalCoaches(4).generalCoachTotalSeats(80).build();
        Mockito.when(trainService.getTrainByNumber(456)).thenThrow(new TrainNumberNotExistException("Train not found"));
        mockMvc.perform(get("/trainInfo-api/trainInfo/{number}",456))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());

    }
    @Test
    void updateTrainInfo() throws Exception{
        TrainInfo t=TrainInfo.builder().trainNumber(76542).trainName("Sarkar").totalKms(200).acCoaches(8).acCoachTotalSeats(80).sleeperCoaches(6).sleeperCoachTotalSeats(50).generalCoaches(4).generalCoachTotalSeats(80).build();
        Mockito.when(trainService.updateTrainInfo(t)).thenReturn(t);
        mockMvc.perform(put("/trainInfo-api/trainInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(t)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(76542));
    }
    @Test
    void updateTrainInfoWithException() throws Exception{
        TrainInfo t=TrainInfo.builder().trainNumber(76542).trainName("Sarkar").totalKms(200).acCoaches(8).acCoachTotalSeats(80).sleeperCoaches(6).sleeperCoachTotalSeats(50).generalCoaches(4).generalCoachTotalSeats(80).build();
        Mockito.when(trainService.updateTrainInfo(t)).thenThrow(new TrainNumberNotExistException("Train not found"));
        mockMvc.perform(put("/trainInfo-api/trainInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(t)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }
    @Test
    void deleteTrainByNumber() throws Exception{
        TrainInfo t=TrainInfo.builder().trainNumber(76542).trainName("Sarkar").totalKms(200).acCoaches(8).acCoachTotalSeats(80).sleeperCoaches(6).sleeperCoachTotalSeats(50).generalCoaches(4).generalCoachTotalSeats(80).build();
        Mockito.when(trainService.deleteTrainByNumber(76542)).thenReturn("Train Deleted Successfully");
        mockMvc.perform(delete("/trainInfo-api/trainInfo/{number}",76542))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Train Deleted Successfully"));
    }
    @Test
    void deleteTrainByNumberWithException() throws Exception{
        TrainInfo t=TrainInfo.builder().trainNumber(76542).trainName("Sarkar").totalKms(200).acCoaches(8).acCoachTotalSeats(80).sleeperCoaches(6).sleeperCoachTotalSeats(50).generalCoaches(4).generalCoachTotalSeats(80).build();
        Mockito.when(trainService.deleteTrainByNumber(456)).thenThrow(new TrainNumberNotExistException("Train not found"));
        mockMvc.perform(delete("/trainInfo-api/trainInfo/{number}",456))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }
}

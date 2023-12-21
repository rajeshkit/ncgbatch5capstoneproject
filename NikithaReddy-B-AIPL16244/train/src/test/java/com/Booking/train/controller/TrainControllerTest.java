package com.Booking.train.controller;

import com.Booking.train.customexception.TrainIdNotFoundException;
import com.Booking.train.model.TrainResources;
import com.Booking.train.services.TrainService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
        TrainResources t = TrainResources.builder().trainNumber(12345L).trainName("hydexpress").totalkms(134.0).acCoaches(35).acCoachTotalSeats(180).sleepercoaching(8).sleeperCoachTotalSeats(40).generalCoaches(8).generalCoachesTotalSeats(500).build();
        Mockito.when(trainService.addTrain(t)).thenReturn(t);
        mockMvc.perform(post("/train-api/Train_Table")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(t)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(12345));

    }

    @Test
    void getAllTrainDetail() throws Exception {
        TrainResources t1 = TrainResources.builder().trainNumber(199L).trainName("kachigudaexpress").totalkms(111.0).acCoaches(10).acCoachTotalSeats(300).sleepercoaching(65).sleeperCoachTotalSeats(90).generalCoaches(40).generalCoachesTotalSeats(10000).build();
        TrainResources t2 = TrainResources.builder().trainNumber(12345L).trainName("hydexpress").totalkms(134.0).acCoaches(35).acCoachTotalSeats(180).sleepercoaching(8).sleeperCoachTotalSeats(40).generalCoaches(8).generalCoachesTotalSeats(500).build();
        Mockito.when(trainService.getAllTrainDetail()).thenReturn(Arrays.asList(t1, t2));
        mockMvc.perform(get("/train-api/Train_Table"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getTrainById() throws Exception {
        TrainResources t = TrainResources.builder().trainNumber(199L).trainName("kachigudaexpress").totalkms(111.0).acCoaches(10).acCoachTotalSeats(300).sleepercoaching(65).sleeperCoachTotalSeats(90).generalCoaches(40).generalCoachesTotalSeats(10000).build();
        Mockito.when(trainService.getTrainById(199L)).thenReturn(t);
        mockMvc.perform(get("/train-api/Train_Table/{id}", 199))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(199));
    }


    @Test
    void getTrainByIdWithException() throws Exception {
        TrainResources t = TrainResources.builder().trainNumber(199L).trainName("kachigudaexpress").totalkms(111.0).acCoaches(10).acCoachTotalSeats(300).sleepercoaching(65).sleeperCoachTotalSeats(90).generalCoaches(40).generalCoachesTotalSeats(10000).build();
        Mockito.when(trainService.getTrainById(12345L)).thenThrow(new TrainIdNotFoundException("Train not found"));
        mockMvc.perform(get("/train-api/Train_Table/{id}", 12345L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }

    @Test
    void updateTrain() throws Exception {
        TrainResources t = TrainResources.builder().trainNumber(199L).trainName("kachigudaexpress").totalkms(111.0).acCoaches(10).acCoachTotalSeats(300).sleepercoaching(65).sleeperCoachTotalSeats(90).generalCoaches(40).generalCoachesTotalSeats(10000).build();
        Mockito.when(trainService.updateTrain(t)).thenReturn(t);
        mockMvc.perform(put("/train-api/Train_Table")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(t)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(199L));
    }

    @Test
    void updateTrainWithException() throws Exception {
        TrainResources t = TrainResources.builder().trainNumber(987L).trainName("kachigudaexpress").totalkms(111.0).acCoaches(10).acCoachTotalSeats(300).sleepercoaching(65).sleeperCoachTotalSeats(90).generalCoaches(40).generalCoachesTotalSeats(10000).build();
        Mockito.when(trainService.updateTrain(t)).thenThrow(new TrainIdNotFoundException("Train not found"));
        mockMvc.perform(put("/train-api/Train_Table")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(t)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteTrainById() throws Exception {
        TrainResources t = TrainResources.builder().trainNumber(199L).trainName("kachigudaexpress").totalkms(111.0).acCoaches(10).acCoachTotalSeats(300).sleepercoaching(65).sleeperCoachTotalSeats(90).generalCoaches(40).generalCoachesTotalSeats(10000).build();
        Mockito.when(trainService.deleteTrainById(199L)).thenReturn("Train Deleted Successfully");
        mockMvc.perform(delete("/train-api/Train_Table/{id}", 199L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Train Deleted Successfully"));
    }

    @Test
    void deleteTrainByIdWithException() throws Exception {
        TrainResources t = TrainResources.builder().trainNumber(199L).trainName("kachigudaexpress").totalkms(111.0).acCoaches(10).acCoachTotalSeats(300).sleepercoaching(65).sleeperCoachTotalSeats(90).generalCoaches(40).generalCoachesTotalSeats(10000).build();
        Mockito.when(trainService.deleteTrainById(12345L)).thenThrow(new TrainIdNotFoundException("Train not found"));
        mockMvc.perform(delete("/train-api/Train_Table/{id}", 12345L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }
}


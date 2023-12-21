package com.trainmicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trainmicroservice.model.Train;
import com.trainmicroservice.service.TrainService;
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

@WebMvcTest(TrainController.class)
class TrainControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TrainService trainService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSaveTrainDetails() throws Exception {
        Train train = Train.builder().trainNumber(1).trainName("Rajasthani Expressss").totalKms(1490)
                .acCoaches(3).acCoachTotalSeats(120)
                .sleeperCoaches(5).sleeperCoachTotalSeats(200)
                .generalCoaches(7).generalCoachTotalSeats(280).build();
        Mockito.when(trainService.saveTrainDetails(train)).thenReturn(train);

        mockMvc.perform(MockMvcRequestBuilders.post("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(train)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.trainNumber").exists());
    }

    @Test
    void testGetAllTrainsDetail() throws Exception {
        Train train1 = Train.builder().trainNumber(1).trainName("Rajasthani Expressss").totalKms(1490)
                .acCoaches(3).acCoachTotalSeats(120)
                .sleeperCoaches(5).sleeperCoachTotalSeats(200)
                .generalCoaches(7).generalCoachTotalSeats(280).build();
        Train train2 = Train.builder().trainNumber(1).trainName("Haryanvi Expressss").totalKms(1000)
                .acCoaches(3).acCoachTotalSeats(120)
                .sleeperCoaches(5).sleeperCoachTotalSeats(200)
                .generalCoaches(7).generalCoachTotalSeats(280).build();
        Mockito.when(trainService.getAllTrainsDetail()).thenReturn(Arrays.asList(train1,train2));

        mockMvc.perform(MockMvcRequestBuilders.get("/train-api/train"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    void testGetTrainDetailsById() throws Exception {
        int trainNumber = 1;
        Train train = Train.builder().trainNumber(1).trainName("Rajasthani Expressss").totalKms(1490)
                .acCoaches(3).acCoachTotalSeats(120)
                .sleeperCoaches(5).sleeperCoachTotalSeats(200)
                .generalCoaches(7).generalCoachTotalSeats(280).build();
        Mockito.when(trainService.getTrainDetailsById(trainNumber)).thenReturn(train);

        mockMvc.perform(MockMvcRequestBuilders.get("/train-api/{id}", trainNumber))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.trainNumber").value(trainNumber));
    }

    @Test
    void testUpdateTrainDetails() throws Exception {
        Train train = Train.builder().trainNumber(1).trainName("Rajasthani Expressss").totalKms(1090)
                .acCoaches(3).acCoachTotalSeats(120)
                .sleeperCoaches(5).sleeperCoachTotalSeats(200)
                .generalCoaches(7).generalCoachTotalSeats(280).build();
        Mockito.when(trainService.updateTrainDetails(train)).thenReturn(train);

        mockMvc.perform(MockMvcRequestBuilders.put("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(train)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.trainNumber").exists());
    }

    @Test
    void testDeleteTrainDetails() throws Exception {
        int trainNumber = 1;
        Mockito.when(trainService.deleteTrainDetails(trainNumber)).thenReturn("Train deleted successfully");

        mockMvc.perform(MockMvcRequestBuilders.delete("/train-api/{id}", trainNumber))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Train deleted successfully"));
    }
}
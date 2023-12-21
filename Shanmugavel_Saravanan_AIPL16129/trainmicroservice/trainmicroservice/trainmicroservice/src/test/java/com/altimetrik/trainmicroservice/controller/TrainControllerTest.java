package com.altimetrik.trainmicroservice.controller;

import com.altimetrik.trainmicroservice.model.Train;
import com.altimetrik.trainmicroservice.service.TrainService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

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
        Train train = new Train();
        train.setTrainNumber(1);
        train.setTrainName("Express");
        train.setTotalKms(500.0);
        train.setAcCoaches(2);
        train.setAcCoachTotalSeats(50);
        train.setSleeperCoaches(5);
        train.setSleeperCoachTotalSeats(200);
        train.setGeneralCoaches(10);
        train.setGeneralCoachTotalSeats(500);

        Mockito.when(trainService.addTrain(Mockito.any(Train.class))).thenReturn(train);
        mockMvc.perform(post("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(train)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(1));
    }

    @Test
    void getAllTrains() throws Exception {
        Train train1 = new Train();
        train1.setTrainNumber(1);
        train1.setTrainName("Express");
        train1.setTotalKms(500.0);
        train1.setAcCoaches(2);
        train1.setAcCoachTotalSeats(50);
        train1.setSleeperCoaches(5);
        train1.setSleeperCoachTotalSeats(200);
        train1.setGeneralCoaches(10);
        train1.setGeneralCoachTotalSeats(500);

        Train train2 = new Train();
        train2.setTrainNumber(2);
        train2.setTrainName("Local");
        train2.setTotalKms(100.0);
        train2.setAcCoaches(1);
        train2.setAcCoachTotalSeats(20);
        train2.setSleeperCoaches(3);
        train2.setSleeperCoachTotalSeats(100);
        train2.setGeneralCoaches(5);
        train2.setGeneralCoachTotalSeats(250);

        Mockito.when(trainService.getAllTrain()).thenReturn(Arrays.asList(train1, train2));

        mockMvc.perform(get("/train-api/train"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getTrainByNumber() throws Exception {
        Train train = new Train();
        train.setTrainNumber(1);
        train.setTrainName("Express");
        train.setTotalKms(500.0);
        train.setAcCoaches(2);
        train.setAcCoachTotalSeats(50);
        train.setSleeperCoaches(5);
        train.setSleeperCoachTotalSeats(200);
        train.setGeneralCoaches(10);
        train.setGeneralCoachTotalSeats(500);

        Mockito.when(trainService.getTrainByNumber(1)).thenReturn(train);

        mockMvc.perform(get("/train-api/train/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(1));
    }

    @Test
    void updateTrain() throws Exception {
        Train train = new Train();
        train.setTrainNumber(1);
        train.setTrainName("Express");
        train.setTotalKms(500.0);
        train.setAcCoaches(2);
        train.setAcCoachTotalSeats(50);
        train.setSleeperCoaches(5);
        train.setSleeperCoachTotalSeats(200);
        train.setGeneralCoaches(10);
        train.setGeneralCoachTotalSeats(500);

        Mockito.when(trainService.updateTrain(Mockito.any(Train.class))).thenReturn(train);

        mockMvc.perform(put("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(train)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(1));
    }

    @Test
    void deleteTrainById() throws Exception {
        Mockito.when(trainService.deleteTrainByNumber(1)).thenReturn("Train deleted successfully");

        mockMvc.perform(delete("/train-api/train/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Train deleted successfully"));
    }

//    @Test
//    void testAddTrain() {
//    }
//
//    @Test
//    void testGetTrainByNumber() {
//    }
//
//    @Test
//    void testGetTrainByNumber1() {
//    }
//
//    @Test
//    void testUpdateTrain() {
//    }
//
//    @Test
//    void testDeleteTrainById() {
//    }
//
//    @Test
//    void testAddTrain1() {
//    }
//
//    @Test
//    void testGetTrainByNumber2() {
//    }
//
//    @Test
//    void testGetTrainByNumber3() {
//    }
//
//    @Test
//    void testUpdateTrain1() {
//    }
//
//    @Test
//    void testDeleteTrainById1() {
//    }
}

package com.booking_details.train.controller;

import com.booking_details.train.model.TrainModel;
import com.booking_details.train.service.TrainService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
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

    private static TrainModel trainModel1;
    private static TrainModel trainModel2;

    private static List<TrainModel> trainModelList;

    @BeforeAll
    static void setupModels(){
        trainModel1 = new TrainModel(123,"testTrain",34.5,3,35,4,45,1,10);
        trainModel2 = new TrainModel(234,"testTrain2",54.5,3,35,4,45,1,10);
        trainModelList = new ArrayList<>();
        trainModelList.add(trainModel1);
        trainModelList.add(trainModel2);
    }

    @Test
    void addTrainDetailsTest() throws Exception {
        Mockito.when(trainService.addTrainDetails(trainModel1)).thenReturn(trainModel1);
        mockMvc.perform(post("/train-microservice/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(trainModel1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(123));
    }

    @Test
    void getAllTrainDetailsTest() throws Exception {
        Mockito.when(trainService.getAllTrainDetails()).thenReturn(trainModelList);
        mockMvc.perform(get("/train-microservice/train"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getAllTrainDetailsByIdTest() throws Exception{
        Mockito.when(trainService.getAllTrainDetailsById(123L)).thenReturn(trainModel1);
        mockMvc.perform(get("/train-microservice/train/{id}",123))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(123));
    }

    @Test
    void updateTrainDetailsTest() throws Exception{
        Mockito.when(trainService.updateTrainDetails(trainModel1)).thenReturn(trainModel1);
        mockMvc.perform(put("/train-microservice/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(trainModel1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalKms").value(34.5));
    }

    @Test
    void deleteTrainDetailsByIdTest() throws Exception{
        Mockito.when(trainService.deleteTrainDetailsById(123L)).thenReturn("Deleted..");
        mockMvc.perform(delete("/train-microservice/train/{id}",123))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Deleted..")));
    }
}

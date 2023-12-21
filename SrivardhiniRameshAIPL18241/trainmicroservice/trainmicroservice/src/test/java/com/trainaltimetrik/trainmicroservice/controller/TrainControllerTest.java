package com.trainaltimetrik.trainmicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trainaltimetrik.trainmicroservice.model.Train;
import com.trainaltimetrik.trainmicroservice.service.TrainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TrainControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TrainService trainService;

    @InjectMocks
    private TrainController trainController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(trainController).build();
    }

    @Test
    void addTrain() throws Exception {
        Train train = createSampleTrain();
        when(trainService.addTrain(any(Train.class))).thenReturn(train);

        mockMvc.perform(post("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(train)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(train.getTrainNumber()));
    }

    @Test
    void getAllTrain() throws Exception {
        when(trainService.getAllTrain()).thenReturn(List.of(createSampleTrain()));

        mockMvc.perform(get("/train-api/train"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].trainNumber").value(100));
    }

    @Test
    void getTrainByNumber() throws Exception {
        Train train = createSampleTrain();
        when(trainService.getTrainByNumber(100)).thenReturn(train);

        mockMvc.perform(get("/train-api/train/100"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(100));
    }

    @Test
    void updateTrain() throws Exception {
        Train train = createSampleTrain();
        when(trainService.updateTrain(any(Train.class))).thenReturn(train);

        mockMvc.perform(put("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(train)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(train.getTrainNumber()));
    }

    @Test
    void deleteTrainById() throws Exception {
        when(trainService.deleteTrainByNumber(100)).thenReturn("Train deleted successfully");

        mockMvc.perform(delete("/train-api/train/100"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string("Train deleted successfully"));
    }

    private Train createSampleTrain() {
        return Train.builder()
                .trainNumber(100)
                .trainName("Super Express")
                .totalKms(200)
                .acCoaches(15)
                .acCoachTotalSeats(20)
                .sleeperCoaches(11)
                .sleeperCoachTotalSeats(12)
                .generalCoaches(20)
                .generalCoachTotalSeats(28).build();
    }
}


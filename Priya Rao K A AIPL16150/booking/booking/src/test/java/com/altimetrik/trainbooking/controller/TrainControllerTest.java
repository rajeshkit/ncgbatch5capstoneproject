package com.altimetrik.trainbooking.controller;

import com.altimetrik.trainbooking.controller.TrainController;
import com.altimetrik.trainbooking.model.Train;
import com.altimetrik.trainbooking.service.TrainService;
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
public class TrainControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TrainService trainService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addTrain() throws Exception {
        Train t1 = Train.builder()
                .trainNumber(100).trainName("Vande Bharat").totalKilometers(890).acCoaches(78).totalAcCoachSeats(56).sleeperCoaches(90)
                .totalSleeperCoachSeats(23).generalCoaches(13).totalGeneralCoachSeats(30)
                .build();
        Mockito.when(trainService.addTrain(t1)).thenReturn(t1);
        mockMvc.perform(post("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(t1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(100));

    }

    @Test
    void getAllTrain() throws Exception {
        Train t1 = Train.builder()
                .trainNumber(100).trainName("Vande Bharat").totalKilometers(890).acCoaches(78).totalAcCoachSeats(56).sleeperCoaches(90)
                .totalSleeperCoachSeats(23).generalCoaches(13).totalGeneralCoachSeats(30)
                .build();
        Train t2 = Train.builder()
                .trainNumber(101).trainName("Chennai Express").totalKilometers(679).acCoaches(23).totalAcCoachSeats(32).sleeperCoaches(34)
                .totalSleeperCoachSeats(23).generalCoaches(14).totalGeneralCoachSeats(17)
                .build();
        Mockito.when(trainService.getAlltrain()).thenReturn(Arrays.asList(t1, t2));
        mockMvc.perform(get("/train-api/train"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getTrainByNumber() {
    }

    @Test
    void updateTrain() {
    }

    @Test
    void deleteTrainByNumber() {
    }
}
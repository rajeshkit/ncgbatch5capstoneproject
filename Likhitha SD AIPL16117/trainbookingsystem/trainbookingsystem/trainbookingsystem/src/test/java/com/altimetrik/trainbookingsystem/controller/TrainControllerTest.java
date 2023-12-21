package com.altimetrik.trainbookingsystem.controller;

import com.altimetrik.trainbookingsystem.model.Train;
import com.altimetrik.trainbookingsystem.service.TrainService;
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
                .trainNo(101).trainName("Mysore Express").totalKms(900).acCoaches(50).acCoachTotalSeats(55).sleeperCoaches(60)
                .sleeperCoachTotalSeats(65).generalCoaches(70).generalCoachTotalSeats(75).build();
        Mockito.when(trainService.addTrain(t1)).thenReturn(t1);
        mockMvc.perform(post("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(t1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNo").value(101));

    }

    @Test
    void getAllTrain() throws Exception {
        Train t1 = Train.builder()
                .trainNo(101).trainName("Mysore Express").totalKms(900).acCoaches(50).acCoachTotalSeats(55).sleeperCoaches(60)
                .sleeperCoachTotalSeats(65).generalCoaches(70).generalCoachTotalSeats(75).build();
        Train t2 = Train.builder()
                .trainNo(201).trainName("shatabdi").totalKms(500).acCoaches(78).acCoachTotalSeats(56).sleeperCoaches(90)
                .sleeperCoachTotalSeats(80).generalCoaches(80).generalCoachTotalSeats(67).build();
        Mockito.when(trainService.getAlltrain()).thenReturn(Arrays.asList(t1,t2));
        mockMvc.perform(get("/train-api/train"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getTrainByNo() {
    }

    @Test
    void updateTrain() {
    }

    @Test
    void deleteTrainByNo() {
    }


}

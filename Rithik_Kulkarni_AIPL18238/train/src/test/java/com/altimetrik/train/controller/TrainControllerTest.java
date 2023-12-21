package com.altimetrik.train.controller;

import com.altimetrik.train.model.Train;
import com.altimetrik.train.service.TrainService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import java.util.Arrays;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(TrainController.class)   //To Test the methods of TrainController class.
class TrainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainService trainService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addTrain() throws Exception {

        Train tr1 = Train.builder()
                .trainNumber(111)
                .trainName("Test-tr1")
                .totalKms(999)
                .acCoaches(11)
                .acCoachTotalSeats(333)
                .sleeperCoaches(11)
                .sleeperCoachTotalSeats(333)
                .generalCoaches(11)
                .generalCoachTotalSeats(333)
                .build();

        Mockito.when(trainService.addTrain(tr1)).thenReturn(tr1);
        mockMvc.perform(MockMvcRequestBuilders.post("/train-api/train")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(tr1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(111));
    }

    @Test
    void getAllTrains() throws Exception {

        Train tr1 = Train.builder()
                .trainNumber(111)
                .trainName("Test-tr1")
                .totalKms(999)
                .acCoaches(11)
                .acCoachTotalSeats(333)
                .sleeperCoaches(11)
                .sleeperCoachTotalSeats(333)
                .generalCoaches(11)
                .generalCoachTotalSeats(333)
                .build();

        Train tr2 = Train.builder()
                .trainNumber(222)
                .trainName("Test-tr2")
                .totalKms(888)
                .acCoaches(22)
                .acCoachTotalSeats(444)
                .sleeperCoaches(22)
                .sleeperCoachTotalSeats(444)
                .generalCoaches(22)
                .generalCoachTotalSeats(444)
                .build();

        Mockito.when(trainService.getAllTrains()).thenReturn(Arrays.asList(tr1,tr2));
        mockMvc.perform(get("/train-api/train"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

    }

    @Test
    void getTrainByTrainNumber() throws Exception {

        Train tr1 = Train.builder()
                .trainNumber(111)
                .trainName("Test-tr1")
                .totalKms(999)
                .acCoaches(11)
                .acCoachTotalSeats(333)
                .sleeperCoaches(11)
                .sleeperCoachTotalSeats(333)
                .generalCoaches(11)
                .generalCoachTotalSeats(333)
                .build();

        Mockito.when(trainService.getTrainByTrainNumber(111)).thenReturn(tr1);
        mockMvc.perform(get("/train-api/train/111")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(tr1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(111));

    }

    @Test
    void updateTrain() throws Exception{

        Train tr1 = Train.builder()
                .trainNumber(111)
                .trainName("Test-tr1")
                .totalKms(999)
                .acCoaches(11)
                .acCoachTotalSeats(333)
                .sleeperCoaches(11)
                .sleeperCoachTotalSeats(333)
                .generalCoaches(11)
                .generalCoachTotalSeats(333)
                .build();

        Train tr2 = Train.builder()
                .trainNumber(111).trainName("Test-tr2")
                .totalKms(888)
                .acCoaches(22)
                .acCoachTotalSeats(444)
                .sleeperCoaches(22)
                .sleeperCoachTotalSeats(444)
                .generalCoaches(22)
                .generalCoachTotalSeats(334)
                .build();

        Mockito.when(trainService.updateTrain(tr1)).thenReturn(tr2);
        mockMvc.perform(put("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(tr1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainName").value("Test-tr2"));

    }

}
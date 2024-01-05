package com.trainbooking.trainmicroservices.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.trainbooking.trainmicroservices.model.Train;
import com.trainbooking.trainmicroservices.service.TrainService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TrainController.class)   //To Test the methods of TrainController class.
class TrainControllerTest {

    /*Before executing the controller method we have to test it. For that we need to invoke request mapping
    So to invoke request mapping we create/inject MockMvc object*/
    @Autowired
    MockMvc mockMvc;

    @MockBean
    TrainService trainService;   //To invoke TrainService addTrain() method we need TrainService bean.

    @Autowired
    ObjectMapper objectMapper;   //Used to bind JSON data to java Object.



    @Test
    void addTrain() throws Exception{
        Train train1 = Train.builder()
                .trainNumber(171717).trainName("Vande Bharat").totalKms(700)
                .acCoaches(6).acCoachTotalSeats(300)
                .sleeperCoaches(4).sleeperCoachesTotalSeats(200)
                .generalCoaches(5).generalCoachesTotalSeats(500).build();

        Mockito.when(trainService.addTrain(train1)).thenReturn(train1);
        mockMvc.perform(post("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(train1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(171717));
    }

    @Test
    void getAllTrainDetails() throws Exception{
        Train train1 = Train.builder()
                .trainNumber(171717).trainName("Vande Bharat").totalKms(700)
                .acCoaches(6).acCoachTotalSeats(300)
                .sleeperCoaches(4).sleeperCoachesTotalSeats(200)
                .generalCoaches(5).generalCoachesTotalSeats(500).build();

        Train train2 = Train.builder()
                .trainNumber(181818).trainName("Bullet Train").totalKms(1700)
                .acCoaches(4).acCoachTotalSeats(200)
                .sleeperCoaches(2).sleeperCoachesTotalSeats(200)
                .generalCoaches(6).generalCoachesTotalSeats(600).build();

        Mockito.when(trainService.getAllTrainDetails()).thenReturn(Arrays.asList(train1,train2));
        mockMvc.perform(get("/train-api/train"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getTrainByTrainNumber() throws  Exception{
        Train train1 = Train.builder()
                .trainNumber(171717).trainName("Vande Bharat").totalKms(700)
                .acCoaches(6).acCoachTotalSeats(300)
                .sleeperCoaches(4).sleeperCoachesTotalSeats(200)
                .generalCoaches(5).generalCoachesTotalSeats(500).build();

        Mockito.when(trainService.getTrainByTrainNumber(171717)).thenReturn(train1);
        mockMvc.perform(get("/train-api/train/171717")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(train1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(171717));
    }

    @Test
    void updateTrainDetails() throws Exception {
        Train train1 = Train.builder()
                .trainNumber(171717).trainName("Vande Bharat").totalKms(700)
                .acCoaches(6).acCoachTotalSeats(300)
                .sleeperCoaches(4).sleeperCoachesTotalSeats(200)
                .generalCoaches(5).generalCoachesTotalSeats(500).build();

        Mockito.when(trainService.updateTrainDetails(train1)).thenReturn(train1);
        mockMvc.perform(put("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(train1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(171717));
    }

    @Test
    void deleteTrainByTrainNumber() throws Exception {
        Mockito.when(trainService.deleteTrainByTrainNumber(171717)).thenReturn("Train Details Deleted Successfully !!!");
        mockMvc.perform(MockMvcRequestBuilders.delete("/train-api/train/{trainNumber}", 171717))
                .andExpect(status().isOk());
    }
}

package com.train.trainmicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.train.trainmicroservice.model.Train;
import com.train.trainmicroservice.service.TrainService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import java.util.Arrays;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    void addTrain() throws Exception{
        Train t1 = Train.builder()
                .trainNumber(123).trainName("jp-udz").totalKms(700)
                .acCoaches(6).acCoachesTotalSeats(3)
                .sleeperCoaches(4).sleeperCoachTotalSeats(33)
                .generalCoaches(5).generalCoachTotalSeats(33).build();

        Mockito.when(trainService.addTrain(t1)).thenReturn(t1);
        mockMvc.perform(post("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(t1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(123));
    }

   @Test
   void getAllTrainDetails() throws Exception{
       Train t1 = Train.builder()
               .trainNumber(123).trainName("jp-udz").totalKms(700)
               .acCoaches(6).acCoachesTotalSeats(3)
               .sleeperCoaches(4).sleeperCoachTotalSeats(33)
               .generalCoaches(5).generalCoachTotalSeats(33).build();

       Train t2 = Train.builder()
               .trainNumber(13).trainName("jp-cor").totalKms(500)
               .acCoaches(6).acCoachesTotalSeats(3)
               .sleeperCoaches(4).sleeperCoachTotalSeats(33)
               .generalCoaches(5).generalCoachTotalSeats(33).build();

        Mockito.when(trainService.getAllTrain()).thenReturn(Arrays.asList(t1,t2));
        mockMvc.perform(get("/train-api/train"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getTrainByTrainNumber() throws  Exception{
        Train t1 = Train.builder()
                .trainNumber(123).trainName("jp-udz").totalKms(700)
                .acCoaches(6).acCoachesTotalSeats(3)
                .sleeperCoaches(4).sleeperCoachTotalSeats(33)
                .generalCoaches(5).generalCoachTotalSeats(33).build();
        Mockito.when(trainService.getTrainById(123)).thenReturn(t1);
        mockMvc.perform(get("/train-api/train/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(t1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(123));
    }

    @Test
    void updateTrainDetails() throws Exception {
        Train t1 = Train.builder()
                .trainNumber(123).trainName("jp-udz").totalKms(700)
                .acCoaches(6).acCoachesTotalSeats(3)
                .sleeperCoaches(4).sleeperCoachTotalSeats(33)
                .generalCoaches(5).generalCoachTotalSeats(33).build();
        Train t2 = Train.builder()
                .trainNumber(13).trainName("jp-cor").totalKms(500)
                .acCoaches(6).acCoachesTotalSeats(3)
                .sleeperCoaches(4).sleeperCoachTotalSeats(33)
                .generalCoaches(5).generalCoachTotalSeats(33).build();

        Mockito.when(trainService.updateTrain(t1)).thenReturn(t2);
        mockMvc.perform(put("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(t1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainName").value("jp-cor"));
    }

}

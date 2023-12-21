package com.TrainBookingSystem.Train.Controller;


import com.TrainBookingSystem.Train.Services.TrainService;
import com.TrainBookingSystem.Train.model.TrainResources;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
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

    @MockBean
    private TrainService trainService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void addTrain() throws Exception {


        TrainResources t1=TrainResources.builder().trainNumber(12345L).trainName("gfsgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();

        Mockito.when(trainService.addTrainResources(t1)).thenReturn(t1);
        mockMvc.perform(post("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(t1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(12345L));

    }

    @Test
    void allTrainDetail() throws Exception {
        TrainResources t1=TrainResources.builder().trainNumber(12345L).trainName("gfsgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();

        TrainResources t2=TrainResources.builder().trainNumber(456345L).trainName("uyutsgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();


        Mockito.when(trainService.getAllTrainDetail()).thenReturn(Arrays.asList(t1,t2));

        mockMvc.perform(get("/train-api/train"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getTrainById() throws Exception {

        TrainResources t1=TrainResources.builder().trainNumber(12345L).trainName("gfsgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();

        Mockito.when(trainService.getTrainById(12345L)).thenReturn(t1);

        mockMvc.perform(get("/train-api/train/{id}",12345L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(12345L));

    }

    @Test
    void updateTrainDetail() throws Exception {
        TrainResources t1=TrainResources.builder().trainNumber(12345L).trainName("gfsgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();


        Mockito.when(trainService.updateTrainDetail(t1)).thenReturn(t1);

        mockMvc.perform(put("/train-api/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(t1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(12345L));


    }

    @Test
    void deleteTrainById() throws Exception {
        TrainResources t1=TrainResources.builder().trainNumber(12345L).trainName("gfsgjh").totalKms(12.2).acCoaches(5).acCoachTotalSeats(20)
                .sleeperCoaches(5).sleeperCoachTotalSeats(50).generalCoaches(5).generalCoachesTotalSeats(50).build();


        Mockito.when(trainService.deleteTrainById(12345L)).thenReturn(String.valueOf(t1));

        mockMvc.perform(delete("/train-api/train/{id}",12345L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}
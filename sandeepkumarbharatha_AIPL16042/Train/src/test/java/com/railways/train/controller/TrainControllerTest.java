package com.railways.train.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.railways.train.exceptions.TrainNumberNotFound;
import com.railways.train.model.Train;
import com.railways.train.services.TrainServices;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TrainController.class)
class TrainControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TrainServices trainServices;  //overrides RouteserviceImpl
    @Autowired
    private ObjectMapper objectMapper;  //converts java objects to json
    @InjectMocks
    private TrainController trainController;


    @Test
    void trainDetails() throws Exception {
        Train train = Train.builder().trainName("narayanadri").trainNumber(1234)
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Mockito.when(trainServices.addTrainDetails(train)).thenReturn(train);
        mockMvc.perform(post("/train-api/traindetails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(train)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(train.getTrainNumber()));

    }

    @Test
    void getTrainByNumber() throws Exception {
        Train train = Train.builder().trainName("narayanadri").trainNumber(1234)
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Mockito.when(trainServices.getTrainByNumber(train.getTrainNumber()))
                .thenReturn(train);
        mockMvc.perform(get("/train-api/traindetails/{id}", train.getTrainNumber())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(train.getTrainNumber()));
    }

    @Test
    void getTrainByNumberWithException() throws Exception {
        Train train = Train.builder().trainName("narayanadri").trainNumber(1234)
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Mockito.when(trainServices.getTrainByNumber(train.getTrainNumber())).thenThrow(new TrainNumberNotFound("Train number not found sandeep try for other train number"));
        mockMvc.perform(get("/train-api/traindetails/{id}", train.getTrainNumber()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Train number not found sandeep try for other train number"));

    }

    @Test
    void getAllDetails() throws Exception {
        Train train = Train.builder().trainName("narayanadri").trainNumber(1234)
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Train trainDemo = Train.builder().trainName("narayanadri").trainNumber(1234)
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Mockito.when(trainServices.getAllDetails()).thenReturn(Arrays.asList(trainDemo, train));
        assertEquals(2, trainServices.getAllDetails().size());
        mockMvc.perform(get("/train-api/traindetails")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));


    }

    @Test
    void updateTainDetails() throws Exception {
        Train train = Train.builder().trainName("narayanadri").trainNumber(1234)
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Mockito.when(trainServices.updateTainDetails(train)).thenReturn(train);
        mockMvc.perform(put("/train-api/traindetails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(train)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(train.getTrainNumber()));

    }

    @Test
    void updateTrainDetailsWithException() throws Exception {
        Train train = Train.builder().trainName("narayanadri").trainNumber(1234)
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Mockito.when(trainServices.updateTainDetails(train)).thenThrow(new TrainNumberNotFound("Train number not found"));
        mockMvc.perform(put("/train-api/traindetails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(train)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Train number not found"));


    }

    @Test
    void deleteByTrainNumber() throws Exception {
        Train train = Train.builder().trainNumber(1234).trainName("satapddhi express")
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Mockito.when(trainServices.deleteByTrainNumber(train.getTrainNumber())).thenReturn("train details deleted successfully");
        mockMvc.perform(delete("/train-api/traindetails/{id}", train.getTrainNumber())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("train details deleted successfully")));

    }

    @Test
    void deleteByTrainNumberWithException() throws Exception {
        Train train = Train.builder().trainName("narayanadri").trainNumber(1234)
                .totalKms(450).acCoaches(2).acCoachTotalSeats(12)
                .sleeperCoaches(5).sleeperCoachTotalSeats(10)
                .generalCoaches(9).generalCoacheTotalSeats(300).build();
        Mockito.when(trainServices.deleteByTrainNumber(train.getTrainNumber())).thenThrow(new TrainNumberNotFound("Train number not found"));
        mockMvc.perform(delete("/train-api/traindetails/{id}", train.getTrainNumber()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Train number not found"));

    }

}

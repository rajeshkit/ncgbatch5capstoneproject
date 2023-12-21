package com.altimetrik.trainmicroservice.controller;


import com.altimetrik.trainmicroservice.exceptions.TrainIdNotExistsException;
import com.altimetrik.trainmicroservice.model.Train;
import com.altimetrik.trainmicroservice.service.TrainService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TrainController.class)
@ExtendWith(MockitoExtension.class)
class TrainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainService trainService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testDeleteTrainByIdSuccess() throws Exception {
        String trainId = "T1234";

        // Mocking the trainService to return a value when deleteTrainByNumber is called
        doReturn("Train with ID T1234 deleted successfully.").when(trainService).deleteTrainByNumber(trainId);

        mockMvc.perform(delete("/trains/{id}", trainId))
                .andExpect(status().isOk())
                .andExpect(content().string("Train with ID T1234 deleted successfully."));
    }



    @Test
    void testDeleteTrainByIdNotFound() throws Exception {
        // Mocking the trainService to throw TrainIdNotExistsException
        doThrow(TrainIdNotExistsException.class).when(trainService).deleteTrainByNumber(any());

        mockMvc.perform(delete("/trains/{id}", "T1234"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").value("Cannot delete. Train with ID T1234 not found."));
    }

    @Test
    void testUpdateTrainSuccess() throws Exception {
        Train train = new Train();
        train.setTrainNumber("T1234");
        train.setTrainName("Express");
        // Mocking the trainService to return the updated train
        when(trainService.updateTrain(any(Train.class))).thenReturn(train);

        mockMvc.perform(MockMvcRequestBuilders.put("/trains")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(train)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value("T1234"));
    }

    @Test
    void testUpdateTrainNotFound() throws Exception {
        Train train = new Train();
        train.setTrainNumber("T1234");
        train.setTrainName("Express");
        // Mocking the trainService to throw TrainIdNotExistsException
        doThrow(TrainIdNotExistsException.class).when(trainService).updateTrain(any(Train.class));

        mockMvc.perform(MockMvcRequestBuilders.put("/trains")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(train)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").value("Cannot update. Train with trainNumber T1234 not found."));
    }

    @Test
    void testAddTrainSuccess() throws Exception {
        Train train = new Train();
        train.setTrainNumber("T1234");
        train.setTrainName("Express");
        // Mocking the trainService to return the added train
        when(trainService.addTrain(any(Train.class))).thenReturn(train);

        mockMvc.perform(MockMvcRequestBuilders.post("/trains")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(train)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.trainNumber").value("T1234"));
    }

    @Test
    void testAddTrainValidationFailure() throws Exception {
        // Mocking validation failure scenario
        Train train = new Train();

        mockMvc.perform(MockMvcRequestBuilders.post("/trains")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(train)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetAllTrainsSuccess() throws Exception {
        Train train1 = new Train();
        train1.setTrainNumber("T1234");
        train1.setTrainName("Express");

        Train train2 = new Train();
        train2.setTrainNumber("T1245");
        train2.setTrainName("Superfast");

        // Mocking the trainService to return a list of trains
        when(trainService.getAllTrains()).thenReturn(Arrays.asList(train1, train2));

        mockMvc.perform(MockMvcRequestBuilders.get("/trains"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }


    @Test
    void testGetTrainByIdSuccess() throws Exception {
        Train train = new Train();
        train.setTrainNumber("T1234");
        train.setTrainName("Express");

        // Mocking the trainService to return a train by ID
        when(trainService.getTrainByNumber(any(String.class))).thenReturn(train);

        mockMvc.perform(MockMvcRequestBuilders.get("/trains/{id}", "T1234"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value("T1234"));
    }

    @Test
    void testGetTrainByIdNotFound() throws Exception {
        // Mocking the trainService to throw TrainIdNotExistsException
        doThrow(TrainIdNotExistsException.class).when(trainService).getTrainByNumber(any(String.class));

        mockMvc.perform(MockMvcRequestBuilders.get("/trains/{id}", "T1234"))
                .andExpect(status().isNotFound());
    }
}

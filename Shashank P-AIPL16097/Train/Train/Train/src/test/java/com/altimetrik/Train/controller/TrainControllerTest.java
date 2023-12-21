package com.altimetrik.Train.controller;

import com.altimetrik.Train.exception.TrainIdNotExistsException;
import com.altimetrik.Train.model.Train;
import com.altimetrik.Train.repository.TrainRepository;
import com.altimetrik.Train.service.TrainService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(TrainController.class)
public class TrainControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private TrainService trainService;
    private TrainRepository trainRepository;


    @Test
    void addTrain() throws Exception{
        Train t1=Train.builder()
                .trainId(1)
                .trainName("wadeyar express")
                .acCoaches(15)
                .acCoachTotalSeats(100)
                .sleeperCoaches(20)
                .sleeperCoachTotalSeats(200)
                .generalCoaches(25)
                .generalCoachTotalSeats(300)
                .totalKms(150)
                .build();
        when(trainService.addTrain(t1)).thenReturn(t1);

        mockMvc.perform(post("/train-api/train").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(t1))).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainId").value(1));;

    }
    @Test
    void updateTrain() throws TrainIdNotExistsException {
        Train trainToUpdate = Train.builder()
                .trainId(101)
                .trainName("Updated Train")
                .build();

        when(trainService.updateTrain(trainToUpdate)).thenReturn((trainToUpdate));
        Train updatedTrain = trainService.updateTrain(trainToUpdate);

        assertEquals(trainToUpdate, updatedTrain);
    }

    @Test
    void deleteTrainById() throws TrainIdNotExistsException {
        int trainIdToDelete = 101;
        Train trainToDelete = Train.builder()
                .trainId(trainIdToDelete)
                .trainName("Train to Delete")
                .build();

        when(trainService.deleteTrainById(trainIdToDelete)).thenReturn("deleted succesfully");

        String deletionMessage = trainService.deleteTrainById(trainIdToDelete);

        assertEquals("deleted succesfully", deletionMessage);

    }

    @Test
    void getAllTrains() {
        List<Train> trainList = Arrays.asList(
                Train.builder().trainId(101).trainName("Train 1").build(),
                Train.builder().trainId(102).trainName("Train 2").build()

        );

        when(trainService.getAllTrains()).thenReturn(trainList);

        List<Train> retrievedTrains = trainService.getAllTrains();

        assertEquals(trainList.size(), retrievedTrains.size());
    }

    @Test
    void getTrainById() throws TrainIdNotExistsException {
        int trainId = 101;
        Train train = Train.builder()
                .trainId(trainId)
                .trainName("Sample Train")
                .build();

        when(trainService.getTrainById(trainId)).thenReturn((train));

        Train retrievedTrain = trainService.getTrainById(trainId);

        assertEquals(train, retrievedTrain);
    }

}

package com.finalproject.railwaysmicroservice;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.finalproject.railwaysmicroservice.controller.RailwaysController;
import com.finalproject.railwaysmicroservice.model.Railway;
import com.finalproject.railwaysmicroservice.service.RailwayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RailwayControllerTesting {

    private MockMvc mockMvc;


    @Mock
    private RailwayService railwayService;


    @InjectMocks
    private RailwaysController railwaysController;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(railwaysController).build();


    }



    @Test
    public void testAddTrain() throws Exception {
        Railway railway = Railway.builder()
                .trainNumber(1)
                .trainName("rajdhani")
                .totalKms(100)
                .acCoaches(20)
                .generalCoaches(1)
                .sleeperCoaches(5)
                .acCoachesTotalSeats(72)
                .generalCoachesTotalSeats(72)
                .sleeperCoachesTotalSeats(72)
                .build();

        when(railwayService.addTrain(any(Railway.class))).thenReturn(railway);

        mockMvc.perform(post("/railways-api/railways")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(railway)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.trainNumber").exists());
    }



    @Test
    public void testGetAllTrains() throws Exception {
        Railway railwayno1 = Railway.builder()
                .trainNumber(1)
                .trainName("rajdhani")
                .totalKms(100)
                .acCoaches(20)
                .generalCoaches(1)
                .sleeperCoaches(5)
                .acCoachesTotalSeats(72)
                .generalCoachesTotalSeats(72)
                .sleeperCoachesTotalSeats(72)
                .build();

        Railway railwayno2 = Railway.builder()
                .trainNumber(2)
                .trainName("sha")
                .totalKms(100)
                .acCoaches(20)
                .generalCoaches(1)
                .sleeperCoaches(5)
                .acCoachesTotalSeats(72)
                .generalCoachesTotalSeats(72)
                .sleeperCoachesTotalSeats(72)
                .build();
        List<Railway> railways = Arrays.asList(railwayno1, railwayno2);

        when(railwayService.getAllTrains()).thenReturn(railways);

        mockMvc.perform(get("/railways-api/railways"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }




    @Test
    public void testGetTrainById() throws Exception {


        int trainNumber = 1;


        Railway railway = Railway.builder()
                .trainNumber(2)
                .trainName("sha")
                .totalKms(100)
                .acCoaches(20)
                .generalCoaches(1)
                .sleeperCoaches(5)
                .acCoachesTotalSeats(72)
                .generalCoachesTotalSeats(72)
                .sleeperCoachesTotalSeats(72)
                .build();
        Optional<Railway> optionalRailway = Optional.of(railway);



        when(railwayService.getTrainById(trainNumber)).thenReturn(Optional.of(optionalRailway));

        mockMvc.perform(get("/railways-api/railways/{trainNumber}", trainNumber))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.trainNumber").value(2));
    }

    @Test
    public void testUpdateTrain() throws Exception {
        Railway railway = Railway.builder()
                .trainNumber(2)
                .trainName("sha")
                .totalKms(100)
                .acCoaches(20)
                .generalCoaches(1)
                .sleeperCoaches(5)
                .acCoachesTotalSeats(72)
                .generalCoachesTotalSeats(72)
                .sleeperCoachesTotalSeats(72)
                .build();


        when(railwayService.updateTrain(any(Railway.class))).thenReturn(railway);



        mockMvc.perform(put("/railways-api/railways")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(railway)))


                       .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                       .andExpect(jsonPath("$.trainNumber").exists());
    }



    @Test
    public void testDeleteTrain() throws Exception {


            int trainNumber = 1;

           mockMvc.perform(delete("/railways-api/railways/{trainNumber}", trainNumber))
                   .andExpect(status().isOk())
                   .andExpect(content().string("Train is deleted from the table "));

           verify(railwayService, times(1)).deleteTrain(trainNumber);


    }
}

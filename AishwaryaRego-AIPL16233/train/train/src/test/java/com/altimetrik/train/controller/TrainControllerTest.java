package com.altimetrik.train.controller;

import com.altimetrik.train.model.Train;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TrainController.class)
@AutoConfigureMockMvc
public class TrainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllTrains() throws Exception {
        mockMvc.perform(get("/train-api/train/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetTrainByNum() throws Exception {
        int trainNum = 1;
        mockMvc.perform(post("/train-api/train/{num}", trainNum)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddTrain() throws Exception {
        Train newTrain = new Train();
        mockMvc.perform(post("/train-api/train/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newTrain)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateTrain() throws Exception {
        Train existingTrain = new Train();
        mockMvc.perform(put("/train-api/train/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(existingTrain)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteTrain() throws Exception {
        int trainNum = 1;
        mockMvc.perform(delete("/train-api/train/{num}", trainNum)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

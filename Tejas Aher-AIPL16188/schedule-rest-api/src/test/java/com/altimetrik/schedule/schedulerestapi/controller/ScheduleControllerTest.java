package com.altimetrik.schedule.schedulerestapi.controller;

import com.altimetrik.schedule.schedulerestapi.model.NewScheduleRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.util.Date;

@WebMvcTest(ScheduleController.class)
class ScheduleControllerTest {

    @MockBean
    private ScheduleController scheduleController;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addScheduleRequest() throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date arrivalDateTime = dateFormat.parse("2023-12-19T10:00:00");
        Date departureDateTime = dateFormat.parse("2023-12-19T15:00:00");

        NewScheduleRequest newScheduleRequest = NewScheduleRequest.builder().arrivalDateTime(arrivalDateTime).departureDateTime(departureDateTime).trainId("1").routeId("2").build();

        String jsonTrain = objectMapper.writeValueAsString(newScheduleRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/schedule-api/newScheduleRequest").contentType(MediaType.APPLICATION_JSON).content(jsonTrain)).andDo(MockMvcResultHandlers.print()) // Print the request and response for debugging
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
package com.altimetrikfinalproject.schedule.controller;

import com.altimetrikfinalproject.schedule.entity.NewScheduleRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ScheduleController.class)
class ScheduleControllerTest {

        @MockBean
        private ScheduleController scheduleController;
        @Autowired
        private MockMvc mockMvc;
        @Autowired
        private ObjectMapper objectMapper;

        @Test
        void newScheduleRequest() throws Exception {
            NewScheduleRequest scheduleRequest = NewScheduleRequest.builder().arrivalDateTime(new Date("2023-12-19T15:00:00")).departureDateTime(new Date("2023-12-19T15:00:00")).routeId(1).trainId(2).build();
            String jsonTrain = objectMapper.writeValueAsString(scheduleRequest);

            mockMvc.perform(MockMvcRequestBuilders.post("/schedule-service/newRequest").contentType(MediaType.APPLICATION_JSON).content(jsonTrain)).andDo(MockMvcResultHandlers.print()) // Print the request and response for debugging
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }
}